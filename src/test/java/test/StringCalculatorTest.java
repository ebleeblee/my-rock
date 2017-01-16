package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator cal;
	@Before
	public void setUp(){
		cal = new StringCalculator();
	}
	
	@Test
	public void add_null(){
		assertEquals(0, cal.add(null));
	}
	
	@Test
	public void add_쉼표덧셈(){
		assertEquals(3, cal.add("1,2"));
	}
	
	@Test
	public void add_쉼표콜론덧셈(){
		assertEquals(6, cal.add("1,2:3"));
	}
	
	@Test
	public void add_구분자(){
		assertEquals(6, cal.add("//;\n1;2"));
	}
	

}
