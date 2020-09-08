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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.UUID;

import epicentr.services.StorageService;

@Controller
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepository;

    private final StorageService storageService;

    public GalleryController(StorageService storageService) {
        this.storageService = storageService;
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
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            String name = UUID.randomUUID().toString()+"." + FilenameUtils.getExtension(file.getOriginalFilename());;
            try {
                byte[] bytes = file.getBytes();
                String pathdir = System.getProperty("user.dir");
                System.out.println("+++---------"+pathdir);
                // Creating the directory to store file

                Path f = storageService.load("");
                String rootPath= f.toUri().getPath();
                System.out.println("---------"+rootPath);

                File dir = new File(rootPath + File.separator );

                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream2 = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream2.write(bytes);
                stream2.close();

                Gallery gallery = new Gallery();
                gallery.setName(name);
                galleryRepository.save(gallery);
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return "redirect:/";
    }

}
