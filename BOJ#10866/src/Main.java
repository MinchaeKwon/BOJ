//덱
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Deque<Integer> deque = new LinkedList<>();
		
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			String s = sc.next();
			
			switch(s) {
			case "push_front":
				int f = sc.nextInt();
				deque.addFirst(f);
				
				break;
			case "push_back":
				int b = sc.nextInt();
				deque.addLast(b);
				
				break;
			case "pop_front":
				if(deque.isEmpty())
					System.out.println("-1");
				else
					System.out.println(deque.removeFirst());
				break;
			case "pop_back":
				if(deque.isEmpty())
					System.out.println("-1");
				else
					System.out.println(deque.removeLast());
				break;
			case "size":
				System.out.println(deque.size());
				break;
			case "empty":
				if(deque.isEmpty())
					System.out.println("1");
				else
					System.out.println("0");
				break;
			case "front":
				if(deque.isEmpty())
					System.out.println("-1");
				else
					System.out.println(deque.peekFirst());
				break;
			case "back":
				if(deque.isEmpty())
					System.out.println("-1");
				else
					System.out.println(deque.peekLast());
				break;
			}
		}
		
		sc.close();

	}

}

