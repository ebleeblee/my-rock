package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Question;
import rock.domain.QuestionRepository;

@Controller
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(Question question){
		System.out.println("qa"+question);
		
		questionRepository.save(question);
		return "redirect:/";
	}
	
	
}
