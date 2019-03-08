package finalproject.alongtheway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import finalproject.alongtheway.model.Businesses;

@Controller
public class AlongTheWayController {

	@Autowired
	private BusinessSearchApiService businessSearchService;
	
	@RequestMapping("/")
	public ModelAndView list() {

		return new ModelAndView("index");

	}

	@RequestMapping("/results")
	public ModelAndView results() { 
		List<Businesses> results;
		results = businessSearchService.getAllResultsByLocation();
		ModelAndView mav = new ModelAndView("results", "results", results);
		return mav;
	}
}
