/**
 * 1863 스카이라인 쉬운거
 * https://www.acmicpc.net/problem/1863
 * 
 * @author minchae
 * @date 2024. 10. 5.
 * */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 높이가 낮아지면 그 전에 넣어놨던 건물을 pop
			while (!stack.isEmpty() && stack.peek() > y) {
				answer++;
				stack.pop();
			}
			
			// 현재 높이와 스택에 저장된 높이가 같은 경우 같은 건물이므로 넘어감
			if (!stack.isEmpty() && stack.peek() == y) {
				continue;
			}
			
			// 
			stack.push(y);
		}
		
		// 남은 건물이 있는 경우
		while (!stack.isEmpty()) {
			// 건물 높이가 있는 경우
			if (stack.peek() > 0) {
				answer++;
			}
			
			stack.pop();
		}
		
		System.out.println(answer);
	}

}
