package test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert .*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	Calculator calc;
	
	//테스트 메소드마다 초기화한다. 상태값이 다른 메소드에 영향을 미치지 않도록.
	
	@Before
	public void setUp(){
		calc = new Calculator();
		
	}

	@Test
	public void add() {
		//assertThat(calc.add(3,4), is(7)); //영어권
		assertEquals(7, calc.add(3, 4));
	}
	
	@Test
	public void minus() throws Exception {
		int result = calc.minus(4, 1);
		assertEquals(3, result);
	}
	
	
	
	@After
	public void teardown(){
		
	}

}
