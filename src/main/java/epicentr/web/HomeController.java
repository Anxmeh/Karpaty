package epicentr.web;

import epicentr.entities.Message;
import epicentr.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import epicentr.repositories.MessageRepository;
import epicentr.repositories.UserRepository;

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
