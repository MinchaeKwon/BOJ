/**
 * 1918 후위 표기식
 * https://www.acmicpc.net/problem/1918
 * 
 * @author minchae
 * @date 2021.3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String expression = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			
			switch (c) {
			case '+': case '-': case '*': case '/': // 연산자일 경우
				// 스택이 비어있지 않고 현재 연산자보다 스택에 있는 연산자 우선순위보다 큰 경우 스택에 있는 연산자 출력(우선순위가 높은 것을 출력)
				while (!stack.empty() && prec(c) <= prec(stack.peek())) {
					System.out.print(stack.pop());
				}
				
				// 출력 후 우선순위가 낮은 연산자 스택에 넣음
				stack.push(c);
				break;
			case '(': // 왼쪽 괄호일 경우 스택에 넣음 
				stack.push(c);
				break;
			case ')': // 오른쪽 괄호일 경우 왼쪽 괄호를 만날 때까지 출력 				
				char top = stack.pop();
				
				while (top != '(') {
					System.out.print(top);
					top = stack.pop();
				}
				
				break;
			default: // 피연산자는 그냥 출력 
				System.out.print(c);
				break;
			}
		}
		
		// 스택에 남아있는 괄호, 연산자 출력 
		while (!stack.empty()) {
			System.out.print(stack.pop());
		}

	}
	
	// 연산자 우선순위 반환 
	public static int prec(char op) {
		switch (op) {
		case '(': case ')':
			return 0;
		case '+': case '-':
			return 1;
		case '*': case '/':
			return 2;
		}
		
		return -1;
	}

}
