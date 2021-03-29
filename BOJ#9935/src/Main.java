/**
 * 9935 문자열 폭발
 * https://www.acmicpc.net/problem/9935
 * 
 * @author minchae
 * @date 2021. 3. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			// 폭발 문자열의 길이가 스택사이즈보다 같거나 클 경우 -> 작을 경우에는 폭발 할 수 없음
			if (stack.size() >= bomb.length()) {
				boolean flag = true;
				
				for (int j = 0; j < bomb.length(); j++) {
					// 스택에서 문자열을 하나씩 꺼내면서 폭발 문자열인지 확인함 
					// -> 폭발 문자열의 길이만큼 확인하기 위해 (stack.size() - bomb.length() + j)에 해당하는 인덱스부터 스택에서 가져와 확인함 
					// ex) 폭발 문자열이 C4일때 C가 있는지 먼저 확인하고 4가 있는지를 확인하는 것
					if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
				
			}
			
		}
		
		if (stack.empty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			
			for (char c: stack) {
				sb.append(c);
			}
			
			System.out.println(sb.toString());
		}

	}

}
