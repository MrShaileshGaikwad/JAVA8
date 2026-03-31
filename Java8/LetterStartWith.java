package Java8;

import java.util.List;

public class LetterStartWith {
	public static void main(String[] args) {
		List<String> names= List.of("shailesh","Akash","nikhil","Ramesh","Lokesh","Alice");
		
		List<String> n=names.stream().filter(name->name.startsWith("A")).toList();
		System.err.println(n);
		System.err.println();
	}

}
