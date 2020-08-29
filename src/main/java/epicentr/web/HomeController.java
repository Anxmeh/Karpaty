package epicentr.web;

import epicentr.entities.Gallery;
import epicentr.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Ramesh Fadatare
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private GalleryRepository galleryRepository;

	@GetMapping("/")
	public String home(Model model)
	{
		List<Gallery> list = galleryRepository.findAll();
		model.addAttribute("images", list);
		return "index";
	}
	

}
