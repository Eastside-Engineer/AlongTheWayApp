package finalproject.alongtheway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("/results")
	public ModelAndView results(@RequestParam(name = "waypoints", required = true) List<Coordinates> coords) {
		List<Businesses> results = new ArrayList<Businesses>();
		List<Businesses> fullResults = new ArrayList<Businesses>();
		for (Coordinates coord : coords) {
			results = businessSearchService.getAllResultsByCoord(coord.getLatitude(), coord.getLongitude());
			for (Businesses busi : results) {
				fullResults.add(busi);
			}
		}
		ModelAndView mav = new ModelAndView("results", "results", fullResults);
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

	@RequestMapping("/directions")
	public ModelAndView direction(@RequestParam("location1") String location1,
			@RequestParam("location2") String location2) {

		List<Steps> steps = googleApiService.getWaypoints(location1, location2);
		Steps step;
		Long lat1;
		Long long1;
		Coordinates coord = new Coordinates();
		List<Coordinates> waypoints = new ArrayList<Coordinates>();

		for (int i = 0; i < steps.size(); i++) {

			step = steps.get(i);
			lat1 = step.getStartLocation().getStartLat();
			long1 = step.getEndLocation().getEndLong();
			coord.setLatitude(lat1);
			coord.setLongitude(long1);
			waypoints.add(coord);

		}

		// yo, peep that 7, mang(that is the 8th step in the route, mang)

		ModelAndView mav = new ModelAndView("results");

		mav.addObject("waypoints", waypoints);

		return mav;
	}

}
