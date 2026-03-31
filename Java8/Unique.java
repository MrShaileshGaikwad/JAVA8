package Java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Unique {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<Integer> list= Arrays.asList(1,2,1,3,4,5,2,4,5,7,3,3,4,5,67,8,6,7);
		Set<Integer> seen =  new HashSet<>();
		
		
		
		List<Integer>  unique= list.stream().distinct().filter(sort->seen.add(sort)).toList(); //[1, 2, 4, 5, 7, 3, 67, 8, 6]
		System.err.println(unique);
		
	}
//[3, 4, 5, 7] //list.stream().filter(sort->!seen.add(sort)).toList();
}
