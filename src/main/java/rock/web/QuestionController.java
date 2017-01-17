package rock.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import rock.service.AnswerService;
import rock.service.QuestionService;
import rock.service.UserService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(Question question,HttpSession session){
		System.out.println("qa"+question);
		
		String userId = (String) session.getAttribute("userId");
		//User user = userRepository.findOne(userId);
		User user = userService.findByUserId(userId);
		
		question.setWriter(user);
		System.out.println("qa"+question);
		
		//현재시간
	//	LocalDateTime date = LocalDateTime.now();
	//	question.setCreateDate(date);
		
		questionService.save(question);
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String qnaForm(){
		System.out.println("qna화면출력");
		
		return "qna/form";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model){
		//System.out.println("오는지 확인!!!!!!!");
		//System.out.println("id:"+ id);
		Question question = questionService.findOne(id);
		
		int answerCount = question.getAnswers().size();
		model.addAttribute("answerCount",answerCount);
		model.addAttribute("question", question);
		//return "qna/show?answerCount=answerCount";
		return "qna/show";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session){
		User loginUser = (User) session.getAttribute("sessionUser");
		Question question = questionService.findOne(id);
		
		
		if(question.matchUser(loginUser)){
			System.out.println("**********true***********");
			List<Answer>answers = question.getAnswers();
			
			System.out.println("answers:"+ answers);
			activeQuestion(loginUser, question, answers);
			
		}
		
		return "redirect:/";
	}

	private void activeQuestion(User loginUser, Question question, List<Answer> answers) {
		if(question.isEmptyAnswer() || isTrue(question, answers)){
			//삭제 가능 -> 플래그바꾸고, 로그쌓기
			question.changeDeleteFlag();
			question.deleteLog(loginUser);
			questionService.save(question);
		}
	}

	private boolean isTrue(Question question, List<Answer> answers) {
		boolean flag = true;
		for(int i=0; i<answers.size(); i++){
			
			Answer answer = answers.get(i);
			boolean result = question.matchAnswerUser(answer);
			if(result!=true){
				System.out.println("질문자 작성자 다름");
				flag = false;
				break;
			}
			
			return flag;
			
		}
		return flag;
	}
	
	
	
	
	
	
}
