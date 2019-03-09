package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.entity.Element;
import finalproject.alongtheway.model.Businesses;

@Controller
public class AlongTheWayController {

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
		element = businessSearchService.findDistanceAndDuration("", "");
		String distance = element.getDistance().getText();
		String duration = element.getDuration().getText();

		ModelAndView mav = new ModelAndView("matrix");
		mav.addObject("distance", distance);
		mav.addObject("duration", duration);

		return mav;
	}

}
