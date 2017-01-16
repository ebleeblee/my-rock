package rock.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.User;
import rock.domain.UserRepository;
import rock.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//@PostMapping("/users")
	@PostMapping("")
	public String create(User user){
		System.out.println("버튼-회원가입클릭");
		
		//사용자데이터저장하는 클래스를 만들고 그 클래스로 데이터를 받는다.(User 클래스)
		
		System.out.println("User: "+user);
		
		userService.save(user);
		
		return "redirect:/users";
	}
	
	//@GetMapping("/users")
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String form(){
		System.out.println("헤더-회원가입클릭");
		return "user/form";
	}
	
	//정보수정화면
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model){
		System.out.println("id:"+ id);
		model.addAttribute("users",userService.findOne(id) );
		System.out.println("users:"+userService.findOne(id));
		return "/user/updateForm";
	}
	
	
	@PutMapping("/{id}")
	public String update(User newUser, HttpSession session, @PathVariable Long id){
		/*System.out.println("회원정보수정");
		System.out.println("user123:"+user);
		String password = user.getPassword();
		User ur =  (User) session.getAttribute("sessionUser");
		System.out.println("pw:"+ur);
		
		if(ur.getPassword().equals(password)){
			System.out.println("비밀번호 일 치 확인");
			
			//db에서 받아아서 체크
			//User dbUser = (User) userRepository.findAll();
			user.update(user);
			
			userRepository.save(user);
		}
		
		//userRepository.save(user);
		
		return "redirect:/users";*/
		
		
		System.out.println("업뎃");
		//id로 디비 유저를 받아옴 그 유저에 새롭게 입력한 유저를 업뎃함.
		User user = userService.findOne(id);
		user.update(newUser);
		userService.save(user);
		
		return "redirect:/users";
		
	}
	
	//@PutMapping("/")
	
	/*@GetMapping("/updateForm")
	public String update(User user){
		userRepository.save(user);
	}*/
	
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		
		User user = userService.findByUserId(userId);
		if(user!=null){
			
			//user.matchPassword(updatedUser)
			
			//비밀번호 체크
			if(userId.equals(user.getId()) || password.equals(user.getPassword())){
				//로그인 성공
				System.out.println("pw성공");
				session.setAttribute("sessionUser", user);
				session.setAttribute("userId", userId);
				return "redirect:/";
			}else{
				System.out.println("비밀번호x");
				return "redirect:/";
			}
			
			
		}else{
			//로그인 실패
			System.out.println("실패");
			return "redirect:/";
		}
		
		
		
	}
	
	
}
