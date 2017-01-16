package rock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Question;
import rock.domain.QuestionRepository;
@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	
	
	public void save(Question question) {
		questionRepository.save(question);
	}


	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}
	
	public void delete(Question question){
		questionRepository.delete(question);
	}
	
	
	
}
