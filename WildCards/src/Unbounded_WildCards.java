import java.util.Arrays;
import java.util.List;

public class Unbounded_WildCards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> listint=Arrays.asList(10,20,30,40);
		
		List<Double> doublelist=Arrays.asList(11.5, 13.6, 67.8, 43.7);
		
		printList(listint);
		printList(doublelist);
		
		
	}
	private static void printList(List<?>list) {
		
		System.out.println(list);
	}

}
