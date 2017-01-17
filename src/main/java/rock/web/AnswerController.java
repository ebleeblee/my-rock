package rock.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.net.SyslogOutputStream;
import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.domain.UserRepository;
import rock.service.AnswerService;
import rock.service.QuestionService;
import rock.service.UserService;

@Controller
@RequestMapping("/questions/{id}/answers")
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;
	
	
	@PostMapping("")
	public String createAnswer(@PathVariable Long id, Answer answer, Model model, HttpSession session){
		System.out.println("id:"+ id);
		System.out.println("답변하기 버튼 클릭");
		System.out.println("answer:"+ answer);
		
		String userId = (String) session.getAttribute("userId");
		User user = userService.findByUserId(userId);
		answer.setAwriter(user);
		Question question = questionService.findOne(id);
		answer.setQuestionto(question);
		
		//현재시간
		LocalDateTime date = LocalDateTime.now();
		//answer.setCreateDate(date);
		System.out.println("4answer:"+answer);
		answerService.save(answer);
		//model.addAttribute("answer",answer);
		
		return "redirect:/questions/{id}";
	}
	
	//답글 삭제
	@DeleteMapping("/{answerId}")
	public String delete(@PathVariable Long answerId, @PathVariable Long id){
		System.out.println("delete method");
		answerService.delete(answerId);
		
		return "redirect:/questions/{id}";
	}
	
}
