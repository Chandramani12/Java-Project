import java.util.Arrays;
import java.util.List;

public class UpperBound {
public static void main(String[] args) {
	

	// TODO Auto-generated method stub
	
	List<Integer> listint=Arrays.asList(10,20,30,40);
	
	System.out.println(sum(listint));
	
	List<Double> listdouble=Arrays.asList(13.2, 15.6, 9.7, 22.5);
	
	System.out.println(sum(listdouble));
	
	

}
private static Double sum(List< ?extends Number> mylist) {
	
	double sum=0.0;
	
	for(Number iterator:mylist) {
		sum=sum+iterator.doubleValue();
	}
	
	return sum;
}
}
