package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.User;
import rock.domain.UserRepository;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	//@PostMapping("/users")
	@PostMapping("")
	public String create(User user){
		//사용자데이터저장하는 클래스를 만들고 그 클래스로 데이터를 받는다.(User 클래스)
		
		System.out.println("User: "+user);
		
		userRepository.save(user);
		return "redirect:/users";
	}
	
	//@GetMapping("/users")
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
}
