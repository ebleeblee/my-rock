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

@Controller
@RequestMapping("/questions/{id}/answers")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("")
	public String createAnswer(@PathVariable Long id, Answer answer, Model model, HttpSession session){
		System.out.println("id:"+ id);
		System.out.println("답변하기 버튼 클릭");
		System.out.println("answer:"+ answer);
		
		String userId = (String) session.getAttribute("userId");
		User user = userRepository.findByUserId(userId);
		answer.setWriter(user);
		
		Question question = questionRepository.findOne(id);
		answer.setQuestionto(question);
		System.out.println("***answer:"+ answer);
		
		//현재시간
		LocalDateTime date = LocalDateTime.now();
		answer.setCreateDate(date);
		
		answerRepository.save(answer);
		//model.addAttribute("answer",answer);
		
		return "redirect:/questions/{id}";
	}
	
	
	@DeleteMapping("/{answerId}")
	public String delete(@PathVariable Long answerId, @PathVariable Long id){
		System.out.println("delete method");
		answerRepository.delete(answerId);
		
		return "redirect:/questions/{id}";
	}
	
}
