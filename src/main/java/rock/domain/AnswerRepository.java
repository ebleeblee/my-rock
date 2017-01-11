package rock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	Answer findByQuestionto(Long id);

	//Answer findAll(Long id);
	//Answer findByQId(Long id);

}
