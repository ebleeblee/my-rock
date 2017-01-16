package rock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.AnswerRepository;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;

	public void save(Answer answer) {
		answerRepository.save(answer);
	}

	public void delete(Long answerId) {
		answerRepository.delete(answerId);
	}
	
	public void findOne(Long answerId){
		answerRepository.findOne(answerId);
	}

	/*public List<Answer> findAll(Long qaId) {
		// TODO Auto-generated method stub
		return answerRepository.findAnswer(qaId);
		//answerRepository.findAll()
	}*/
	//answerReposi
	
}
