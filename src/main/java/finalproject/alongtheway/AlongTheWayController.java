package finalproject.alongtheway;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.dao.Route;
import finalproject.alongtheway.dao.RoutesDao;
import finalproject.alongtheway.dao.Stop;
import finalproject.alongtheway.waypointsbeans.Legs;
import finalproject.alongtheway.waypointsbeans.Steps;
import finalproject.alongtheway.yelpbeans.Businesses;
import finalproject.alongtheway.yelpbeans.Coordinates;

@Controller
public class AlongTheWayController {

	@Autowired
	RoutesDao dao;

	@Autowired
	private GoogleApiService googleApiService;

	@Autowired
	private BusinessSearchApiService businessSearchService;

	@RequestMapping("/")
	public ModelAndView index(HttpSession session) {
		return new ModelAndView("index");
	}

	@RequestMapping("/endsession")
	public ModelAndView endsession(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/submitform")
	public ModelAndView formsubmit(@RequestParam(name = "location1") String location1,
			@RequestParam(name = "location2") String location2, @RequestParam(name = "category") String category,
			@RequestParam(name = "minrating") Double minrating, HttpSession session) {
		// when first clicking submit button from index page, add the variables to the
		// session
		session.setAttribute("location1", location1);
		session.setAttribute("location2", location2);
		session.setAttribute("category", category);
		session.setAttribute("minrating", minrating);
		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}

	@RequestMapping("/contacts")
	public ModelAndView contacts() {
		return new ModelAndView("contacts");
	}

	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(name = "yelpid") String yelpId,
			@SessionAttribute(name = "location1") String location1,
			@SessionAttribute(name = "location2") String location2,
			@SessionAttribute(name = "category") String category, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Stop> stops = (List<Stop>) session.getAttribute("stops");

		if (stops == null) {
			stops = new ArrayList<Stop>();
			session.setAttribute("stops", stops);
		}

		// get the business as a Businesses class object at the stop by yelp id
		Businesses busi = businessSearchService.getResultById(yelpId);

		// create a new stop to be added to the list of stops
		// using the business returned by the yelpid, set the parameters
		Stop stop = new Stop();

		stop.setYelpId(yelpId);
		stop.setName(busi.getName());
		stop.setCity(busi.getLocation().getCity());
		stop.setState(busi.getLocation().getState());

		// add this created stop to the session List<Stop> stops
		stops.add(stop);

		// call google api to get directions for amended time and distance calc
		List<Legs> legs = googleApiService.getAmendedDirections(location1, location2, stops);

		String totalDist = total1(legs);
		String totalTime = total2(legs);
		session.setAttribute("distanceNew", totalDist);
		session.setAttribute("durationNew", totalTime);

		// redirect to the results page, no need to add objects to model since the same
		// info is already in the session
		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}
	
	// GET DAVID OR MARIAH THIS SHOULD WORK ARRRRRRRGGGGHHHHHHH!!!!!!!
	@RequestMapping("/deleteStop")
	public ModelAndView deleteStop(
			@SessionAttribute(name="stops", required = true) List<Stop> stops,
			@RequestParam(value="stopToRemove", required = true) String stopToRemove,
			HttpSession session) {
		System.out.println(stopToRemove);
		
		for (Stop s : stops) {
			System.out.println(s);
			if (s.getYelpId().equals(stopToRemove)) {
				System.out.println(s + "i");
				stops.remove(s);
			}
		}
		
		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}
	
	@RequestMapping("/saveroute")
	public ModelAndView saveroute(
			@SessionAttribute(name = "location1", required = true) String location1,
			@SessionAttribute(name = "location2", required = true) String location2,
			@SessionAttribute(name = "stops", required = false) List<Stop> stops, 
			HttpSession session) {

		Route route = new Route();
		route.setLocation1(location1);
		route.setLocation2(location2);
		route.setStops(stops);
		dao.create(route);
		return new ModelAndView("redirect:/results");
	}

	@RequestMapping("/matrix")
	public ModelAndView showRoutes(HttpSession session) {
		// return list of all items in DB and pass to model
		List<Route> theRoutes = dao.findAll();
		return new ModelAndView("matrix", "amend", theRoutes);
	}

	@RequestMapping("/delete")
	public ModelAndView deleteRouteForm(@RequestParam("id") Long id) {
		dao.delete(id);
		ModelAndView mav = new ModelAndView("redirect:/matrix");
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView editRouteForm(@RequestParam("id") Long id, HttpSession session) {
		session.removeAttribute("location1");
		session.removeAttribute("location2");
		session.removeAttribute("stops");
		session.removeAttribute("fullResults");
		
		Route route = dao.findById(id);
		String location1 = route.getLocation1();
		String location2 = route.getLocation2();
		// in order to avoid Hibernate lazy initialization problem, copy the stops into
		// a regular ArrayList.
		route.setStops(new ArrayList<>(route.getStops()));

		List<Stop> stops = route.getStops();

//		for (int i = 0; i<stops.size(); i++) {
//			stops.set(i, new Stop(stops.get(i)));
//			stops.get(i).setRoute(null);
//		}
		session.setAttribute("location1", location1);
		session.setAttribute("location2", location2);
		session.setAttribute("category", "landmarks");
		session.setAttribute("stops", stops);
		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}

//	 when populating the results page, we want to return the set of results
//	 generated from each waypoint along the way as a single list
	@RequestMapping("/results")
	public ModelAndView results(@SessionAttribute(name = "location1") String location1,
			@SessionAttribute(name = "location2") String location2,
			@SessionAttribute(name = "category", required = false) String category,
			@SessionAttribute(name = "minrating", required = false) Double minrating,
			@SessionAttribute(name = "stops", required = false) List<Stop> stops,
			@SessionAttribute(name = "steps", required = false) List<Steps> steps,
			@SessionAttribute(name = "waypoints", required = false) List<Coordinates> waypoints,
			@SessionAttribute(name = "fullResults", required = false) List<Businesses> fullResults,
			HttpSession session) {

		// define the steps along the way from the google directions api
		if (steps == null) {
			steps = googleApiService.getWaypoints(location1, location2);
			session.setAttribute("steps", steps);
		}

		// initialize the waypoints to be a list of Coordinates (paired lat and long)
		if (waypoints == null) {
			waypoints = new ArrayList<Coordinates>();
			// set save the waypoints list to the session if empty at first
			session.setAttribute("waypoints", waypoints);
		}

		int i = 0;
		for (Steps stepwp : steps) {
			// set the first coordinates in the list to be the starting location, as steps
			// doesn't include location 0
			if (i == 0) {
				Coordinates coord = new Coordinates();
				coord.setLatitude(stepwp.getStartLocation().getStartLat());
				coord.setLongitude(stepwp.getStartLocation().getStartLong());
				waypoints.add(coord);
			}

			// store each lat and long from the list of steps in a list of Coordinates
			// called waypoints
			Coordinates coord = new Coordinates();
			coord.setLatitude(stepwp.getEndLocation().getEndLat());
			coord.setLongitude(stepwp.getEndLocation().getEndLong());
			if (stepwp.getDistance().getValue() > 4000) {
				waypoints.add(coord);
			}
			i++;
		}

		// fullResults will be a list of all results from all waypoints
		if (fullResults == null) {
			fullResults = new ArrayList<Businesses>();
			session.setAttribute("fullResults", fullResults);		
			List<String> names = new ArrayList<String>();
			
			for (Coordinates coordinates : waypoints) {
				// results will take in the yelp response from each waypoint
				List<Businesses> results = businessSearchService.getAllResultsByCoordByCategory(coordinates.getLatitude(),
						coordinates.getLongitude(), category);
	
				for (Businesses busi : results) {
					if (!names.contains(busi.getId())) {
						names.add(busi.getId());
						// return fullResults from all waypoints for items rated 4.0 or higher
						if (minrating == null) {
							minrating = 4.0;
						}
						if (busi.getRating() >= minrating) {
							fullResults.add(busi);
						}
					}
				}
			}
		}

		Legs leg = googleApiService.getBasicDirections(location1, location2);
		String dist = leg.getDistance().getText();
		String time = leg.getDuration().getText();

		ModelAndView mav = new ModelAndView("results", "results", fullResults);

		String[] parseLoc1 = location1.split(",");
		String[] parseLoc2 = location2.split(",");

		if (stops != null && !stops.isEmpty()) {
			String waypointsUrlPart = "&waypoints=";

			String safeLoc = "";

			for (int j = 0; j < stops.size(); j++) {

				if (j > 0) {

					safeLoc = "|" + URLEncoder.encode(stops.get(j).getCity()) + ","
							+ URLEncoder.encode(stops.get(j).getState());

				} else {
					safeLoc = URLEncoder.encode(stops.get(j).getCity()) + ","
							+ URLEncoder.encode(stops.get(j).getState());
				}

				waypointsUrlPart += safeLoc;
				// System.out.println(waypointsUrlPart);

			}

			// String moreStops = stops.stream().map(stop ->
			// stop.getName()).collect(Collectors.joining("|"));

			mav.addObject("waypointsUrlPart", waypointsUrlPart);
		}

		mav.addObject("loc1", parseLoc1[0] + "+" + parseLoc1[1]);
		mav.addObject("loc2", parseLoc2[0] + "+" + parseLoc2[1]);
		mav.addObject("distance", dist);
		mav.addObject("duration", time);
		return mav;

	}

	// when the user clicks from results page to details
	@RequestMapping("/details/{id}")
	public ModelAndView details(@PathVariable(value = "id") String id) {
		Businesses result = businessSearchService.getResultById(id);
		ModelAndView mav = new ModelAndView("details", "result", result);
		return mav;
	}

	// sum LEGS parsing miles rounding to INT
	private String total1(List<Legs> legs) {

		String tot = "";
		Double totes = 0.0;

		for (int i = 0; i < legs.size(); i++) {

			tot = legs.get(i).getDistance().getText();

			String[] str = tot.split("\\s+");

			totes = totes + Double.parseDouble(str[0].replace(",", ""));
		}

		Integer tot1 = totes.intValue();

		DecimalFormat formatter = new DecimalFormat("#,###");

		return formatter.format(tot1) + " mi";
	}

	// sum LEGS parsing Hours and Minutes rounding to INT
	private String total2(List<Legs> legs) {

		String tot = "";
		Double mins = 0.0;
		Double hours = 0.0;
		Double days = 0.0;

		for (int i = 0; i < legs.size(); i++) {

			tot = legs.get(i).getDuration().getText();

			if (tot.contains("day")) {
				String[] str = tot.split("\\s+");

				days = days + Double.parseDouble(str[0]);

				hours = hours + Double.parseDouble(str[2]);

				//System.out.println(days);
				//System.out.println(hours);

			} else if (tot.contains("hour")) {
				String[] str = tot.split("\\s+");

				hours = hours + Double.parseDouble(str[0]);

				mins = mins + Double.parseDouble(str[2]);

			} else {

				String[] str1 = tot.split("\\s+");
				mins = mins + Double.parseDouble(str1[0]);
			}

		}

		Double hr = mins / 60;
		Double min = mins % 60;
		Double day = days / 24;
		Double newDay = hours / 24;

		Integer tot3 = days.intValue() + day.intValue();
		Integer tot2 = min.intValue();
		Integer tot1 = hours.intValue() + hr.intValue();
		Integer tot4 = newDay.intValue();

		if (days > 0) {
			return tot3 + " day " + tot1 + " hours " + tot2 + " mins";
		} else if (newDay >= 1) {
			return tot4 + " day " + tot1 + " hours " + tot2 + " mins";
		} else {
			return tot1 + " hours " + tot2 + " mins";
		}

	}

}
