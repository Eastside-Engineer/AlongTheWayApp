package finalproject.alongtheway;

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
		session.invalidate();

		return new ModelAndView("index");
	}

	@RequestMapping("/submitform")
	public ModelAndView formsubmit(@RequestParam(name = "location1") String location1,
			@RequestParam(name = "location2") String location2, @RequestParam(name = "category") String category,
			HttpSession session) {
		session.setAttribute("location1", location1);
		session.setAttribute("location2", location2);
		session.setAttribute("category", category);
		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}

	@RequestMapping("/contacts")
	public ModelAndView contacts() {
		return new ModelAndView("contacts");
	}

	@RequestMapping("/add")	
	public ModelAndView add(@RequestParam(name = "latitude") Double latitude,
			@RequestParam(name = "longitude") Double longitude, @RequestParam(name = "yelpid") String yelpId,
			@SessionAttribute(name = "location1") String location1,
			@SessionAttribute(name = "location2") String location2,
			@SessionAttribute(name = "category") String category, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Stop> stops = (List<Stop>) session.getAttribute("stops");
		if (stops == null) {
			stops = new ArrayList<Stop>();
			session.setAttribute("stops", stops);
		}

		// get the business at the stop
		Businesses busi = businessSearchService.getResultById(yelpId);

		Stop stop = new Stop();
		stop.setYelpId(yelpId);
		stop.setName(businessSearchService.getNameById(yelpId));
		stop.setCity(busi.getLocation().getCity());
		stop.setState(busi.getLocation().getState());

		stops.add(stop);

		ModelAndView mav = new ModelAndView("redirect:/results");
		mav.addObject("location1", location1);
		mav.addObject("location2", location2);
		mav.addObject("category", category);
		mav.addObject("stops", stops);

		
		String[] parseBusi1 = location1.split(" ");
		String[] parseBusi2 = location2.split(" ");
		System.out.println(parseBusi1[0] +"+"+ parseBusi1[1]);
		System.out.println(parseBusi2[0] +"+"+ parseBusi2[1]);
		mav.addObject("Busi1", parseBusi1[0] +"+"+ parseBusi1[1]);
		mav.addObject("Busi2", parseBusi2[0] +"+"+ parseBusi2[1]);
		

		mav.addObject("busi", busi);


		return mav;
	}

//	@RequestMapping("/dt")

//	public ModelAndView dist(@SessionAttribute(value = "location1", required = true) String location1,
//			@SessionAttribute(value = "location2", required = true) String location2,
//			@SessionAttribute(value = "stops", required = false) List<Stop> stops) {
//
//		ModelAndView mav = new ModelAndView("test");
//
//		//Legs legs = googleApiService.getAmendedDirections(location1, location2, stops);
//		String distNew = legs.getDistance().getText();
//		String timeNew = legs.getDuration().getText();
//
//		mav.addObject("distanceNew", distNew);
//		mav.addObject("durationNew", timeNew);
//
//		return mav;

	@RequestMapping("/matrix")
	public ModelAndView showRoutes(@SessionAttribute(value = "location1") String location1,
			@SessionAttribute(value = "location2") String location2, @SessionAttribute("stops") List<Stop> stops,
			HttpSession session) {

		Route route = new Route();
		route.setLocation1(location1);
		route.setLocation2(location2);
		route.setStops(stops);

		session.setAttribute("route", route);

		dao.create(route);

		// return list of all items in DB and pass to jsp
		List<Route> TheRoutes = dao.findAll();
		return new ModelAndView("matrix", "amend", TheRoutes);
	}

	@RequestMapping("/delete")
	public ModelAndView deleteRouteForm(@RequestParam("id") Long id) {
		dao.delete(id);
		ModelAndView mav = new ModelAndView("redirect:/matrix");
		return mav;
	}

//	 when populating the results page, we want to return the set of results
//	 generated from each waypoint along the way as a single list
	@RequestMapping("/results")
	public ModelAndView results(@SessionAttribute(name = "location1") String location1,
			@SessionAttribute(name = "location2") String location2,
			@SessionAttribute(name = "category") String category, HttpSession session) {

		// define the steps along the way from the google directions api
		List<Steps> steps = googleApiService.getWaypoints(location1, location2);
		// store each lat and long in a set of Coordinates
		// initialize the waypoints to be a list of coordinates
		List<Coordinates> waypoints = new ArrayList<Coordinates>();
		session.setAttribute("waypoints", waypoints);

		int i = 0;
		for (Steps stepwp : steps) {
			if (i == 0) {
				Coordinates coord = new Coordinates();
				coord.setLatitude(stepwp.getStartLocation().getStartLat());
				coord.setLongitude(stepwp.getStartLocation().getStartLong());
				waypoints.add(coord);
			}
			Coordinates coord = new Coordinates();
			coord.setLatitude(stepwp.getEndLocation().getEndLat());
			coord.setLongitude(stepwp.getEndLocation().getEndLong());
			waypoints.add(coord);
			i++;
		}

		// fullResults will be a list of all results from all waypoints

		List<Businesses> fullResults = new ArrayList<Businesses>();

		for (Coordinates coordinates : waypoints) {
			// results will take in the yelp response from each waypoint
			List<Businesses> results = businessSearchService.getAllResultsByCoordByCategory(coordinates.getLatitude(),
					coordinates.getLongitude(), category);

			List<String> names = new ArrayList<String>();
			// return fullResults from all waypoints for items rated 4.0 or higher
			for (Businesses busi : results) {
				if (!names.contains(busi.getId())) {
					names.add(busi.getId());
					if (busi.getRating() >= 4.0) {
						fullResults.add(busi);
					}
				}
			}
		}

		// return fullResults from all waypoints for items rated 4.0 or higher


		session.setAttribute("location1", location1);
		session.setAttribute("location2", location2);
		session.setAttribute("category", category);

		Legs leg = googleApiService.getBasicDirections(location1, location2);
		String dist = leg.getDistance().getText();
		String time = leg.getDuration().getText();

		ModelAndView mav = new ModelAndView("results", "results", fullResults);
		
		String[] parseLoc1 = location1.split(",");
		String[] parseLoc2 = location2.split(",");
		mav.addObject("loc1", parseLoc1[0] + "+" + parseLoc1[1]);
		mav.addObject("loc2", parseLoc2[0] + "+" + parseLoc2[1]);

		// basic dist/time
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

//	private String splitter(String str1) {
//
//		String[] splitStr = str1.split("\\s+");
//
//		if (!splitStr[1].isEmpty()) {
//			str1 = splitStr[0] + "+" + splitStr[1] + splitStr[2];
//			System.out.println("not empty" + str1);
//		}
//
//		return str1;
//
//	}
}
