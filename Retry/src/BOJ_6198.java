/**
 * 6198 옥상 정원 꾸미기
 * https://www.acmicpc.net/problem/6198
 * 
 * @author minchae
 * @date 2024. 2. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_6198 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		long result = 0;
		
		while (N-- > 0) {
			int height = Integer.parseInt(br.readLine());
			
			// 새로 입력 받은 건물의 높이와 이전 빌딩의 높이가 같거나 작은 경우 옥상을 볼 수 없으므로 스택에서 pop
			while (!stack.isEmpty() && stack.peek() <= height) {
				stack.pop();
			}
			
			result += stack.size();
			stack.push(height); // 새로운 건물을 스택에 넣어줌
		}

		System.out.println(result);
	}

}
