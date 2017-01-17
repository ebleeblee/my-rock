package rock.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.service.QuestionService;

@Controller
public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionService questionService;
	
	/*
	@GetMapping("")
	public String home(String name, String age, Model model){
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "index";
	}*/
	@GetMapping("")
	public String list(Model model){
		List<Question> qna = questionService.findAll();
		System.out.println(qna);
		model.addAttribute("qna",qna);
		
		return "index";
	}
	
}
