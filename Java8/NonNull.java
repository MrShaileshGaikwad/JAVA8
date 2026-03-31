package Java8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NonNull {
public static void main(String[] args) {
	List<String> list=Arrays.asList(null,"Java","Python","MySQL","Spring",null);
	
	List<String> n= list.stream().filter(Objects::nonNull).toList();
	System.out.println(n);
}
}
