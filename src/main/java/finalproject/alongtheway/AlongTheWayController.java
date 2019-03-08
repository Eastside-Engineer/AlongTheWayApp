package finalproject.alongtheway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlongTheWayController {



	@RequestMapping("/")
	public ModelAndView list() {

		return new ModelAndView("index");

	}


}
