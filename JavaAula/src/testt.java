import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class testt {
	
	public static void main(String[] args) {
		LinkedList <Integer> linkedList = new LinkedList <Integer>();
		
		Scanner scanner = new Scanner(System.in);
		
		int numeroDeEstimativa = scanner.nextInt();
		
		for(int i = 0; i< numeroDeEstimativa; i++){
			System.out.println("Digite o numero de estimavas: ");
			
			int estimativa = scanner.nextInt();
			
			linkedList.add(estimativa);
		}
		
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Integer  minimo = null;
		Integer maximo = null;
		Integer soma = new Integer(0);
		Integer media =null;
	}

}
