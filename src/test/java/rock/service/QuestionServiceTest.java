package rock.service;


import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.domain.UserRepository;

public class QuestionServiceTest {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	/*@Mock private QuestionRepository questionRepository;
	@Mock private UserRepository userRepository;
	@InjectMocks private QuestionService questionService;*/
	
	/**
	 * 모키토 없이. junit 만으로..
	 *  로그인 사용자와질문한 사람이같아야 한다. 
	 *  • 답변이 없는경우 삭제가 가능하다.
	 *  • 질문자와 답변자가같은 경우 삭제가가능하다. 
	 *  • 질문자와 답변자가다른 경우 답변을삭제할 수 없다.
	 *  질문 데이터를완전히 삭제하는것이 아니라 데이터의상태를 삭제 상태(deleted)로변경한다.
	 *  • 질문 데이터를삭제한 경우 삭제정보(삭제한사람,삭제한시간)를남긴다.
	 */
	
	
	/*@Test
	public void deleteQuestion(){
		List<Answer> answers = new ArrayList<Answer>();
		User user = new User(1L, "eblee", "pw", "eb", "eb@eb");
		Question question = new Question(1L,user,answers,"testTitle","testContents");
		//로그인한 사용자 = 질문한 사용자
		//when()
		
		//삭제
		verify(questionRepository).delete(question);
	}*/
	List<Answer> answers;
	Answer answer;
	User loginUser;
	User delUser;
	Question question;
	Question delQuestion;
	
	
	@Before
	public void setUp(){
		answers = new ArrayList<Answer>();
		loginUser = new User(1L, "eblee", "pw", "eb", "eb@ebl");
		
		question = new Question(1L,loginUser,answers,"testTitle","testContents");
		delQuestion = new Question(2L, loginUser, answers, "delQaTitle", "delQaContents", "");
		
		answer = new Answer(1L, loginUser, question, "answercontents");
		
		delUser = new User(2L,"testDelete","pw", "del", "del@del");
		
		
	}
	
	@Test
	public void matchUser_로그인사용자_질문한사람비교(){
		assertEquals(true,question.matchUser(loginUser) );
	}
	
	@Test
	public void delete_답변_있는지_조사() throws Exception {
		assertEquals(true, question.isEmptyAnswer());
	}
	
	@Test
	public void delete_질문자_답변자_같은지_비교() throws Exception {
		//Answer answer = answerService.
		//assertEquals(true, question.matchAnswerUser(answer));
		
		//get함수 없이 어떻게 user를??
		//answerService.findAll(1L);
		assertEquals(true, question.matchAnswerUser(answer));
	}
	
	@Test
	public void delete_데이터_상태_변경() throws Exception {
		delQuestion.changeDeleteFlag();
		assertEquals("deleted", delQuestion.getDeleteFlag());
	}
	
	@Test
	public void deleteLog_삭제정보남김() throws Exception {
		HttpSession session = null;
		session.setAttribute("loginUser", delUser);
		//Date now = new Date();
		//SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//String delTime = now.format(new Date());
		String delTime = "2017-01-16 06:18";
		//세션의 유저정보를 받아서 삭제 정보를 남겨야 한다.
		delQuestion.deleteLog(loginUser);
		assertEquals(delQuestion.getDeleteNqaUser(), loginUser);
	}
	
	
	
	
}
