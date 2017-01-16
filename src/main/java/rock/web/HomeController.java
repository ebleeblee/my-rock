package rock.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rock.domain.QuestionRepository;

@Controller
public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;
	
	/*
	@GetMapping("")
	public String home(String name, String age, Model model){
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "index";
	}*/
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("qna", questionRepository.findAll());
		System.out.println("qnalist");
		System.out.println("인덱스");
		return "index";
	}
	
}
