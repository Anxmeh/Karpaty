package epicentr.web;

import epicentr.entities.Gallery;
import epicentr.repositories.GalleryRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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

	@Autowired
	ServletContext context;

	@GetMapping("/document")
	public String document()
	{
		return "document";
	}

	//images[]
	@PostMapping("/document")
	public String saveDocument(@RequestParam("name") MultipartFile[] files,
							   RedirectAttributes redirectAttributes)
	{
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			String name = UUID.randomUUID().toString()+"." + FilenameUtils.getExtension(file.getOriginalFilename());;
			try {
				byte[] bytes = file.getBytes();
				String pathdir = System.getProperty("user.dir");
				System.out.println("+++---------"+pathdir);
				// Creating the directory to store file

				String rootPath = pathdir + File.separator +"src" + File.separator+ "main" +
						File.separator +"resources"+ File.separator + "uploads";

				File dir = new File(rootPath);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile2 = new File(dir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream2 = new BufferedOutputStream(
						new FileOutputStream(serverFile2));
				stream2.write(bytes);
				stream2.close();

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return "redirect:/";
	}

}
