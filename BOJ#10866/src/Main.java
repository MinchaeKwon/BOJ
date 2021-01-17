//덱
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Deque<Integer> deque = new LinkedList<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			switch(s) {
			case "push_front":
				int f = Integer.parseInt(st.nextToken());
				deque.addFirst(f);
				
				break;
			case "push_back":
				int b = Integer.parseInt(st.nextToken());
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
		

	}

}

