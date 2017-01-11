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
	
	
	private LocalDateTime createDate;
	
	
	
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}



	public void setWriter(User awriter) {
		this.awriter = awriter;
	}



	public void setQuestionto(Question questionto) {
		this.questionto = questionto;
	}



	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", writer=" +awriter + ", questionto=" + questionto + ", contents="
				+ answerContents + ", createDate=" + createDate + "]";
	}
	
	
	
}
