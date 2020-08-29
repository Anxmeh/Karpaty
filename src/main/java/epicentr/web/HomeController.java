package epicentr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ramesh Fadatare
 *
 */
@Controller
public class HomeController
{
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	

}
