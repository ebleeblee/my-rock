package rock.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_to_writer"))
	private User writer;
	
	@OneToMany(mappedBy="questionto")
	private List<Answer> answers;
	
	
	private String title;
	private String contents;
	private LocalDateTime createDate;
	
	
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	
	

}
