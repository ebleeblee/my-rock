package rock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyRockApplication {

	//임베디드 톰캣일때만 이 메인 메소드가 필요하다
	public static void main(String[] args) {
		SpringApplication.run(MyRockApplication.class, args);
	}
}
