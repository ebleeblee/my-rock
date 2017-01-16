package rock.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import rock.domain.User;
import rock.domain.UserRepository;
//모키토의 러너로 실행한다.
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock private UserRepository UserRepository;
	
	@InjectMocks private UserService userService;
	
	@Test
	public void findOne() {
		User newUser = new User(1L, "userId", "password", "name", "eblee@eblee.com");
		//디비가 없기 때문에 1번이라는 아이디를 가진 걸 요청
		when(UserRepository.findOne(1L)).thenReturn(newUser);
		User user = userService.findOne(1L);
		assertEquals(newUser, user);
	}
	
	//컨트롤스페이스 두번
	@Test
	public void create() throws Exception {
		User user = new User(1L, "userId", "password", "name", "eblee@eblee.com");
		userService.save(user);
		verify(UserRepository).save(user); //리턴값 없을떄 verify 씀/.. return 값 있을 떄 when
		
		//db와 같이 테스트하는건 단위테스트 아님. 통합테스트
	}

}
