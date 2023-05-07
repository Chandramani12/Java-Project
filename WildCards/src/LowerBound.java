import java.util.Arrays;
import java.util.List;

public class LowerBound {
public static void main(String[] args) {
	
	List<Integer> instlist=Arrays.asList(10,20,304,40);
	printOnlyIntegerClassorSuperClass(instlist);
	
	List<Number> numberlist=Arrays.asList(10, 20, 30, 40);
	printOnlyIntegerClassorSuperClass(numberlist);
	
	
}
private static void printOnlyIntegerClassorSuperClass(List<?super Integer> mylist) {
	System.out.println(mylist);
}
}
