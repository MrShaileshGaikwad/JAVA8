[LongestLength.java](https://github.com/user-attachments/files/23764247/LongestLength.java)
package com.stream;

import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LongestLength {
public static void main(String[] args) {
	String s= "I am a shailesh learning java 8 stream api";
	
	List<String> words = Arrays.asList(
            "apple", "bee", "cat", "dog", "apple", "door", "elephant", "cat", "ant", "boat"
    );
	
	
	String name=Arrays.stream(s.split(" ")).max(Comparator.comparing(String::length)).get();
	String name1=Arrays.stream(s.split(" ")).min(Comparator.comparing(String::length)).get();
	System.out.println("max>>"+name+""+"min>>"+name1);
	
	
	 Map<Integer, List<String>> byLen1 = words.stream()
             .collect(groupingBy(String::length));
     System.out.println("byLength: " + byLen1);
}
}
