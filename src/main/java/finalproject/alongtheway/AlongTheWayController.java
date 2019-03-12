package finalproject.alongtheway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.entity.Element;
import finalproject.alongtheway.model.Businesses;
import finalproject.alongtheway.model.Coordinates;
import finalproject.alongtheway.waypoints.Steps;

@Controller
public class AlongTheWayController {

	@Autowired
	private GoogleApiService googleApiService;

	@Autowired
	private BusinessSearchApiService businessSearchService;

	@RequestMapping("/")
	public ModelAndView list() {
		return new ModelAndView("index");
	}

	@RequestMapping("/info")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView("info");
		return mav;
	}

	@RequestMapping("/matrix")
	public ModelAndView distance() {
		Element element;
		element = googleApiService.findDistanceAndDuration("", "");
		String distance = element.getDistance().getText();
		String duration = element.getDuration().getText();

		ModelAndView mav = new ModelAndView("matrix");
		mav.addObject("distance", distance);
		mav.addObject("duration", duration);

		return mav;
	}

	// when populating the results page, we want to return the set of results generated from each waypoint along the way as a single list
	@RequestMapping("/results")
	public ModelAndView results(
			@RequestParam("location1") String location1,
			@RequestParam("location2") String location2) {

		// define the steps along the way from the google directions api
		List<Steps> steps = googleApiService.getWaypoints(location1, location2);
		Steps step;
		Double lat1;
		Double long1;
		// store each lat and long in a set of Coordinates
		Coordinates coord = new Coordinates();
		// initialize the waypoints to be a list of coordinates
		List<Coordinates> waypoints = new ArrayList<Coordinates>();

		// for each step in the google response, add the coordinates to the waypoints list
		for (int i = 0; i < steps.size(); i++) {
			step = steps.get(i);
			lat1 = step.getStartLocation().getStartLat();
			long1 = step.getEndLocation().getEndLong();
			coord.setLatitude(lat1);
			coord.setLongitude(long1);
			waypoints.add(coord);
		}
		
		// results will take in the yelp response from each waypoint, while fullResults will be a list of all results from all waypoints
		List<Businesses> results = new ArrayList<Businesses>();
		List<Businesses> fullResults = new ArrayList<Businesses>();
		for (Coordinates coordinates : waypoints) {
			results = businessSearchService.getAllResultsByCoord(coordinates.getLatitude(), coordinates.getLongitude());
			for (Businesses busi : results) {
				// only fill results if rating is 4.0 or better, filtering the results
				if (busi.getRating() >= 4.0) {
					fullResults.add(busi);
				}
			}
		}
		// return fullResults from all waypoints for items rated 4.0 or higher
		ModelAndView mav = new ModelAndView("results", "results", fullResults);
		return mav;
	}
	
	// when the user clicks from results page to details
	@RequestMapping("/details/{id}")
	public ModelAndView details(
			@PathVariable(value="id") String id) {
		Businesses result = businessSearchService.getResultById(id);
		ModelAndView mav = new ModelAndView("details", "result", result);
		return mav;
	}

}
