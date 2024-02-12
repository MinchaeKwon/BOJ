/**
 * 1662 압축
 * https://www.acmicpc.net/problem/1662
 * 
 * @author minchae
 * @date 2024. 2. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split("");
		
		Stack<String> stack = new Stack<>();
		
		for (String s : input) {
			if (!s.equals(")")) {
				stack.push(s);
			} else {
				int cnt = 0; // 숫자 길이
				
				// (이 나오기 전까지 진행 (괄호를 품)
				while (!stack.peek().equals("(")) {
					String top = stack.pop();
					
					if (top.equals("*")) {
						cnt += Integer.parseInt(stack.pop()); // 문자열 길이인 경우 해당 값을 더해줌
					} else {
						cnt++; // 아닌 경우 문자 길이 1 증가
					}
				}
				
				stack.pop(); // ( 제거
				
				cnt *= Integer.parseInt(stack.pop()); // 압축 해제 (지금까지 나온 길이에 다음에 나오는 숫자를 곱함 - K(Q))
				
				stack.push(String.valueOf(cnt));
				stack.push("*"); // 문자열 길이를 표시하기 위해 *를 넣어서 구분
			}
		}
		
		int answer = 0;
		
		while (!stack.isEmpty()) {
			if (stack.peek().equals("*")) {
				stack.pop();
				answer += Integer.parseInt(stack.pop());
			} else {
				stack.pop();
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
