package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.entity.Element;
import finalproject.alongtheway.model.Businesses;
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
	public ModelAndView results(@RequestParam(name = "location", required = true) String location) {
		List<Businesses> results;
		results = businessSearchService.getAllResultsByLocation(location);
		ModelAndView mav = new ModelAndView("results", "results", results);
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
	public ModelAndView direction() {
		List<Steps> steps;
		Steps step;
		// yo, peep that 7, mang(that is the 8th step in the route, mang)
		step = googleApiService.getWaypoints("", "").get(7);
		Long lat1 = step.getStartLocation().getStartLat();
		Long lat2 = step.getEndLocation().getEndLat();
		Long long1 = step.getStartLocation().getStartLong();
		Long long2 = step.getEndLocation().getEndLong();

		ModelAndView mav = new ModelAndView("directions");
		mav.addObject("lat1", lat1);
		mav.addObject("lat2", lat2);
		mav.addObject("long1", long1);
		mav.addObject("long2", long2);

		return mav;
	}

}
