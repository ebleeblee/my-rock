package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String i) {
		if(isBlank(i)){
			return 0;
		}
		
		if(i.contains(",") || i.contains(",|:") || i.contains(",|\n")){
			String[] numbers = i.split(",|:|\n");
			
			int total = 0;
			for(int j=0; j<numbers.length; j++){
				int k = Integer.parseInt(numbers[j]);
				total += k;
				
			}return total;
			
		}
		
		
		//커스텀구분자
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(i); 
		System.out.println(m);
		if(m.find()){
			
			/*for(int j=0; j<m.groupCount(); j++){
				String customDelemeter = m.group(j);
			}*/
			
			System.out.println(m.groupCount());
			String customDelimeter = m.group(1); 
			System.out.println(customDelimeter); // ;
			
			
			String[] tokens= m.group(2).split(customDelimeter); 
			//return m.group(2).split(customDelimeter);
			System.out.println("tokens"+tokens);
		}
		
		
		return 0;
		
		
		
	

	}

	private boolean isBlank(String i) {
		return i == null || i.isEmpty();
	}

}
