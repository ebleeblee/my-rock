package rock.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.domain.UserRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(Question question,HttpSession session){
		System.out.println("qa"+question);
		
		String userId = (String) session.getAttribute("userId");
		//User user = userRepository.findOne(userId);
		User user = userRepository.findByUserId(userId);
		
		question.setWriter(user);
		System.out.println("qa"+question);
		
		//현재시간
		LocalDateTime date = LocalDateTime.now();
		question.setCreateDate(date);
		
		questionRepository.save(question);
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String qnaForm(){
		System.out.println("qna화면출력");
		
		return "qna/form";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model){
		System.out.println("오는지 확인!!!!!!!");
		System.out.println("id:"+ id);
		Question question = questionRepository.findOne(id);
		
		int answerCount = question.getAnswers().size();
		System.out.println("answerCount"+answerCount);
		//question.setAnswerCount(answerCount);
		model.addAttribute("answerCount",answerCount);
		model.addAttribute("question", question);
		//return "qna/show?answerCount=answerCount";
		return "qna/show";
	}
	
	
	
}
