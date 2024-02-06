/**
 * 2504 괄호의 값
 * https://www.acmicpc.net/problem/2504
 * 
 * @author minchae
 * @date 2024. 2. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		
		int answer = 0;
		int sum = 1;
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < input.length; i++) {
			char c = input[i];
			
			if (c == '(') {
				stack.push('(');
				sum *= 2;
			} else if (c == ')') {
				// 스택이 비어있거나 여는 괄호가 없을 경우는 잘못된 괄호이기 때문에 0을 넣고 switch문 break
				if (stack.isEmpty() || stack.peek() != '(') {
					answer = 0;
					break;
				}
				
				// 바로 이전 값이 '('인 경우 값을 더함
				if (input[i - 1] == '(') {
					answer += sum;
				}
				
				// 닫는 괄호가 나올 경우 2를 다시 나눠줌 (위에서 여는 괄호가 나올 때 2를 곱했기 때문)
				stack.pop();
				sum /= 2;
			} else if (c == '[') {
				stack.push('[');
				sum *= 3;
			} else if (c == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					answer = 0;
					break;
				}
				
				// 바로 이전 값이 '['인 경우 값을 더함
				if (input[i - 1] == '[') {
					answer += sum;
				}
				
				// 닫는 괄호가 나올 경우 3을 다시 나눠줌
				stack.pop();
				sum /= 3;
			}
		}
		
		System.out.println(stack.isEmpty() ? answer : 0);
	}

}
