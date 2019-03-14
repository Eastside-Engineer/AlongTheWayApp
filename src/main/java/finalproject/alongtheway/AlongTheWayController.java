package finalproject.alongtheway;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.dao.Route;
import finalproject.alongtheway.dao.RoutesDao;
import finalproject.alongtheway.entity.Element;
import finalproject.alongtheway.model.Businesses;
import finalproject.alongtheway.model.Coordinates;
import finalproject.alongtheway.waypoints.Steps;

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

	@RequestMapping("/info")
	public ModelAndView category(
			@RequestParam("location1") String location1,
			@RequestParam("location2") String location2) {

		return new ModelAndView("info");
	}

	@RequestMapping("/matrix")
	public ModelAndView distance(
			@RequestParam("location1") String location1,
			@RequestParam("location2") String location2) {

		Element element;
		element = googleApiService.findDistanceAndDuration(location1, location2);

		String distance = element.getDistance().getText();
		String duration = element.getDuration().getText();

		ModelAndView mav = new ModelAndView("matrix");
		mav.addObject("distance", distance);
		mav.addObject("duration", duration);

		return mav;
	}

	// when populating the results page, we want to return the set of results
	// generated from each waypoint along the way as a single list
	@RequestMapping("/results")
	public ModelAndView results(
			@RequestParam("location1") String location1,
			@RequestParam("location2") String location2, 
			@RequestParam("category") String category,
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
			Coordinates coord = new Coordinates();
			coord.setLatitude(stepwp.getEndLocation().getEndLat());
			coord.setLongitude(stepwp.getEndLocation().getEndLong());
			System.out.println(coord.toString() + i);
			waypoints.add(coord);
			i++;
		}
		
		// fullResults will be a list of all results from all waypoints
		List<Businesses> fullResults = new ArrayList<Businesses>();
		
		for (Coordinates coordinates : waypoints) {
			// results will take in the yelp response from each waypoint
			List<Businesses> results = businessSearchService
					.getAllResultsByCoordByCategory(
					coordinates.getLatitude(),
					coordinates.getLongitude(), 
					category);
			
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
