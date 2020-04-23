//요세푸스 문제
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 0; i < n; i++)
			queue.offer(i + 1);
		
		System.out.print("<");
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < k - 1; j++)
				queue.offer(queue.poll());
			System.out.print(queue.poll() + ", ");
		}
		System.out.println(queue.poll() + ">");
		
		sc.close();
	}

}
