package epicentr.web;

import epicentr.entities.Gallery;
import epicentr.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@GetMapping("/addimage")
	public String addimage(Model model)
	{
		return "addimage";
	}

	@PostMapping("/addimage")
	public String saveimage(@RequestParam("name") MultipartFile[] files,
						   RedirectAttributes redirectAttributes)
	{
		return "addimage";
	}
	

}
