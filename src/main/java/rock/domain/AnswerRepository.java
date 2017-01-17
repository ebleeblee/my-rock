package rock.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	Answer findByQuestionto(Long id);

	//Answer findAll(Long id);
	//Answer findByQId(Long id);
	//List<Answer> findAnswer(Long id);
	//List<Answer> findAnswers(Question questionto);
	

}
