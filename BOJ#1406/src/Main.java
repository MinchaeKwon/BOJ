//¿¡µðÅÍ
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		Stack<Object> stack = new Stack<>();
		Stack<Object> tmpStack = new Stack<>();
		
		for(int i = 0; i < s.length(); i++)
			stack.push(s.charAt(i));
		
		for(int i = 0; i < n; i++) {
			String command = br.readLine();
			
			switch(command.charAt(0)) {
			case 'L':
				if(!stack.empty())
					tmpStack.push(stack.pop());
				break;
			case 'D':
				if(!tmpStack.empty())
					stack.push(tmpStack.pop());
				break;
			case 'B':
				if(!stack.empty())
					stack.pop();
				break;
			case 'P':
				stack.push(command.charAt(2));
				break;
			}
		}
		
		while(!stack.empty())
			tmpStack.push(stack.pop());
		
		while(!tmpStack.empty())
			System.out.print(tmpStack.pop());
		
	}

}
