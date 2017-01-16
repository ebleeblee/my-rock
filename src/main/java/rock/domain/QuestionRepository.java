package rock.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import rock.domain.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

	//List<Answer> findById(Long id);
	
}
