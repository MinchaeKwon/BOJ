/**
 * 2493 탑
 * https://www.acmicpc.net/problem/2493
 * 
 * @author minchae
 * @date 2022. 3. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty()) {
				// 맨 위에 있는 탑의 높이가 현재 입력받은 탑의 높이보다 크거나 같다면 맨 위 탑의 번호를 출력
				if (stack.peek()[1] >= height) {
					System.out.print(stack.peek()[0] + " ");
					break;
				}
				
				stack.pop(); // 맨 위 탑의 높이가 입력받은 탑의 높이보다 작은 경우 : 신호를 수신할 수 없으므로 pop
			}
			
			// 스택이 비어있는 경우에 0 출력
			if (stack.isEmpty()) {
				System.out.print("0 ");
			}
			
			stack.push(new int[] {i + 1, height}); // 탑의 번호와 높이를 저장
		}
	}

}
