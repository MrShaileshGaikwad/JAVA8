package Java8;

import java.util.List;
import java.util.stream.Collectors;

public class Even {

	public static void main(String[] args) {
		
		List<Integer> nums= List.of(1,2,3,4,5,6,7,8,9,10);
		List<Integer> s=nums.stream().filter(i->i%2==0).collect(Collectors.toList());
		
		
		System.err.println(s);
		
		
	}
}
