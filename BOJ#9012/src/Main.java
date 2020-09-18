//괄호
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		//sc.nextLine();
		
		for(int i = 0; i < T; i++) {
			String s = sc.next();
			
			if(check_VPS(s) == 1)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		
		sc.close();

	}
	
	public static int check_VPS(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			switch(c) {
			case '(' :
				stack.push(c);
				break;
			case ')' : 
				if(stack.empty())
					return 0;
				else {
					char open_ch = stack.pop();
					
					if(open_ch == '(' && c != ')')
						return 0;
					break;
				}
			}
		}
		
		if(stack.empty() == false)
			return 0;
		return 1;
	}

}
