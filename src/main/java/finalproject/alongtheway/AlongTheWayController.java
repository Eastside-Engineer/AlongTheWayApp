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

	@RequestMapping("/contacts")
	public ModelAndView contacts() {
		return new ModelAndView("contacts");
	}

	@RequestMapping("/add")
	public ModelAndView add(
			@SessionAttribute(name = "location1", required = false) String location1,
			@SessionAttribute(name = "location2", required = false) String location2,
			@RequestParam("latitude") Double latitude, 
			@RequestParam("longitude") Double longitude,
			@RequestParam("yelpid") String id,
			HttpSession session) {

		ModelAndView mav = new ModelAndView("redirect:/results");
		return mav;
	}


	@RequestMapping("/matrix")
	public ModelAndView showRoutes() {
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
	public ModelAndView results(@RequestParam("location1") String location1,
			@RequestParam("location2") String location2, @RequestParam("category") String category,
			HttpSession session) {

		session.setAttribute("location1", location1);
		session.setAttribute("location2", location2);

		Route route = new Route();
		route.setLocation1(location1);
		route.setLocation2(location2);
		dao.create(route);

		// define the steps along the way from the google directions api
		List<Steps> steps = googleApiService.getWaypoints(location1, location2);
		System.out.println(steps); // console print out to see if steps populated properly
		// store each lat and long in a set of Coordinates

		// initialize the waypoints to be a list of coordinates
		List<Coordinates> waypoints = new ArrayList<Coordinates>();
		session.setAttribute("waypoints", waypoints);

		int i = 0;
		for (Steps stepwp : steps) {
			if(i == 0) {
				Coordinates coord = new Coordinates();
				coord.setLatitude(stepwp.getStartLocation().getStartLat());
				coord.setLongitude(stepwp.getStartLocation().getStartLong());
				System.out.println("set start");
				System.out.println(coord.toString() + i);
				waypoints.add(coord);
			} 
			Coordinates coord = new Coordinates();
			System.out.println("set end");
			coord.setLatitude(stepwp.getEndLocation().getEndLat());
			coord.setLongitude(stepwp.getEndLocation().getEndLong());
			System.out.println(coord.toString() + i);
			waypoints.add(coord);
			i++;
		}
		System.out.println(waypoints);

		// fullResults will be a list of all results from all waypoints
		List<Businesses> fullResults = new ArrayList<Businesses>();

		for (Coordinates coordinates : waypoints) {
			// results will take in the yelp response from each waypoint
			List<Businesses> results = businessSearchService.getAllResultsByCoordByCategory(coordinates.getLatitude(),
					coordinates.getLongitude(), category);

			List<String> names = new ArrayList<String>();
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

		ModelAndView mav = new ModelAndView("results", "results", fullResults);

		String[] parseLoc1 = location1.split(",");
		String[] parseLoc2 = location2.split(",");
		mav.addObject("loc1", parseLoc1[0] + "+" + parseLoc1[1]);
		mav.addObject("loc2", parseLoc2[0] + "+" + parseLoc2[1]);
		return mav;
	}

	// when the user clicks from results page to details
	@RequestMapping("/details/{id}")
	public ModelAndView details(@PathVariable(value = "id") String id) {
		Businesses result = businessSearchService.getResultById(id);
		ModelAndView mav = new ModelAndView("details", "result", result);
		return mav;
	}
}
