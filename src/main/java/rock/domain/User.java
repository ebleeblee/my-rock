package rock.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id; //pk
	
	@Column( length = 20, unique=true)
	private String userId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	private String email;
	
	//private LocalDate createdate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public boolean matchPassword(User updatedUser){
		return this.password.equals(updatedUser.getPassword());
	}
	
	/*public void update(User updatedUser){
		if(!matchPassword(updatedUser)){
			throw new IllegalStateException();
		}
		
		this.name = updatedUser.name;
		this.email = updatedUser.email;
	}*/
	
	public void update(User newUser ){
		this.password = newUser.password;
		this.name= newUser.name;
		this.email = newUser.email;
		
	}
	
	
	
}
