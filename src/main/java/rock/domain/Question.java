package rock.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	static final String DEL= "deleted";
	
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
	//private LocalDateTime createDate;
	
	private String deleteFlag;
	
	@ManyToOne
	@JoinColumn(name="fk_delete_user")
	private User deleteNqaUser;
	private LocalDateTime delTime;
	
	public Question(){}
	
	public Question(long id, User writer, List<Answer> answers, String title, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.answers = answers;
		this.title = title;
		this.contents = contents;
		//this.createDate = LocalDateTime.now();
	}
	
	
	public Question(long id, User writer, List<Answer> answers, String title, String contents, String deleteFlag) {
		super();
		this.id = id;
		this.writer = writer;
		this.answers = answers;
		this.title = title;
		this.contents = contents;
		this.deleteFlag = deleteFlag;
	}
	

	public Question(long id, User deleteNqaUser) {
		super();
		this.id = id;
		this.deleteNqaUser = deleteNqaUser;
		this.delTime = LocalDateTime.now();
		//this.delTime = delTime;
	}
	
	public void setDeleteNqaUser(User deleteNqaUser) {
		this.deleteNqaUser = deleteNqaUser;
	}
	
	public void setDelTime() {
		this.delTime =LocalDateTime.now();
	}


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
	/*public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}*/
	
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public User getDeleteNqaUser() {
		return deleteNqaUser;
	}
	
	

	/*@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", answers=" + answers + ", title=" + title + ", contents="
				+ contents + ", createDate=" + createDate + ", deleteFlag=" + deleteFlag + ", deleteNqaUser="
				+ deleteNqaUser + ", delTime=" + delTime + "]";
	}*/
	
	

	//로그인한사용자=질문한사람
	public boolean matchUser(User loginUser){
		//this.writer.getClass()
		//loginUser.getClass()
		System.out.println("loginUser:"+loginUser);
		System.out.println("writer:"+ this.writer);
		//return this.writer.equals(loginUser);
		if(this.writer.getId() == loginUser.getId()){
			System.out.println("트루 찍힘");
			return true;
		}return false;
		
	}
	
	
	
	//답변 있는지 여부
	public boolean isEmptyAnswer(){
		return this.answers.isEmpty();
	}
	

	//질문자=답변자
	public boolean matchAnswerUser(Answer answer) {
		//System.out.println("this writer:"+this.writer);
		//System.out.println("answer writer"+ answer.getAwriter());
		return this.writer.equals(answer.getAwriter());
	}
	public void changeDeleteFlag() {
		//question.deleteFlag = DEL;
		this.deleteFlag=DEL;
	}


	public void deleteLog(User delUser) {
		System.out.println("user:"+delUser);
		this.deleteNqaUser = delUser;
		//this.delTime = delTime;
		//Date date = new Date();
	   // System.out.println(date);
		///this.delTime =  now;
		
	}
	
	/*public Iterator<Question> notDeletedQuestion(List<Question> qna){
		
		for(int i=0; i<qna.size(); i++){
			if(qna.get(i).deleteFlag!=DEL){
				
			}
		}
		
		Iterator<Question> itr = qna.iterator();
		//ArrayList<Question> notDeletedList = new ArrayList<>();
		while(itr.hasNext()){
			if(itr.next().deleteFlag==DEL){
				itr.remove();
			}
		}
		
		
		return itr;
	}*/
	
	/*public Iterator<Question> notDelQna(Iterator<Question> qna){
		
		List<Question> qnaList = new ArrayList<Question>();
		for(Iterator<Question>it= qnaList.iterator(); it.hasNext(); ){
			Question notDelQuestion = it.next();
			if(notDelQuestion.deleteFlag==DEL){
				it.remove();
			}
		}
		return notDelQuestion;
		
	}*/

	
	

}
