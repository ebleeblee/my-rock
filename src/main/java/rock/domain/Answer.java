package rock.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue
	private long answerId;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_writer"))
	private User awriter;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_question"))
	private Question questionto;
	
	private String answerContents;
	
	
	//private LocalDateTime createDate;
	
	public Answer(){}
	public Answer(long answerId, User awriter, Question question, String answerContents) {
		super();
		this.answerId = answerId;
		this.awriter = awriter;
		this.questionto = question;
		this.answerContents = answerContents;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public void setAwriter(User awriter) {
		this.awriter = awriter;
	}
	
	public User getAwriter() {
		return awriter;
	}

	public void setQuestionto(Question questionto) {
		this.questionto = questionto;
	}

	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", awriter=" + awriter + ", questionto=" + questionto
				+ ", answerContents=" + answerContents + "]";
	}

	/*public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}*/
	/*@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", awriter=" + awriter + ", questionto=" + questionto
				+ ", answerContents=" + answerContents + ", createDate=" + createDate + "]";
	}
*/
	
	
	
	
	
	
	
	
}
