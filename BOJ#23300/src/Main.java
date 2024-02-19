/**
 * 23300 웹 브라우저 2
 * https://www.acmicpc.net/problem/23300
 * 
 * @author minchae
 * @date 2024. 2. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken()); 
		
		Stack<Integer> front = new Stack<>();
		Stack<Integer> back = new Stack<>();
		
		int cur = 0;
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			String input = st.nextToken();
			
			switch (input) {
			case "B":
				if (!back.isEmpty()) {
					front.add(cur);
					cur = back.pop();
				}
				
				break;
			case "F":
				if (!front.isEmpty()) {
					back.add(cur);
					cur = front.pop();
				}
				
				break;
			case "A":
				front.clear(); // 앞으로 가기 공간에 저장된 모든 페이지 삭제
				
				if (cur != 0) {
					back.add(cur);
				}
				
				cur = Integer.parseInt(st.nextToken());
				
				break;
			case "C":
				Stack<Integer> tmp = new Stack<>();
				
				while (!back.isEmpty()) {
					int num = back.pop();
					
					// 비어있거나, 페이지기 같지 않을 경우에만 삽입
					if (tmp.isEmpty() || tmp.peek() != num) {
						tmp.add(num);
					}
				}
				
				// 뒤로가기 스택에 새로운 값 삽입
				while (!tmp.isEmpty()) {
					back.add(tmp.pop());
				}
				
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(cur + "\n");
		
		if (back.isEmpty()) {
			sb.append("-1");
		} else {
			while (!back.isEmpty()) {
				sb.append(back.pop() + " ");
			}
		}
		
		sb.append("\n");
		
		if (front.isEmpty()) {
			sb.append("-1");
		} else {
			while (!front.isEmpty()) {
				sb.append(front.pop() + " ");
			}
		}
		
		System.out.println(sb.toString());
	}

}
