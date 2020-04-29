//단어뒤집기2
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Stack<Character> stack = new Stack<>();
		
		String str = sc.nextLine();
		boolean check = false;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '<') {
				check = true;
				
				while(!stack.empty())
					System.out.print(stack.pop());
				System.out.print(str.charAt(i));
			}
			else if(str.charAt(i) == '>') {
				check = false;
				System.out.print(str.charAt(i));
			}
			else if(check) {
				System.out.print(str.charAt(i));
			}
			else {
				if(str.charAt(i) == ' ') {
					while(!stack.empty())
						System.out.print(stack.pop());
					System.out.print(str.charAt(i));
				}
				else {
					stack.push(str.charAt(i));
				}
			}
		}
		
		while(!stack.empty())
			System.out.print(stack.pop());
		
		sc.close();

	}

}