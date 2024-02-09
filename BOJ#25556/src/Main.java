/**
 * 25556 포스택
 * https://www.acmicpc.net/problem/17142
 * 
 * @author minchae
 * @date 2024. 2. 9.
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
		
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer>[] stack = new Stack[4];
		
		for (int i = 0; i < 4; i++) {
			stack[i] = new Stack<>();
			stack[i].add(0);
		}
		
		for (int num : A) {
			boolean flag = false;
			
			for (Stack<Integer> s : stack) {
				// 스택 가장 위에 있는 숫자보다 큰 경우 스택에 삽입
				if (s.peek() < num) {
					s.push(num);
					flag = true;
					
					break;
				}
			}
			
			// 스택에 다 삽입하지 못한 경우 정렬할 수 없는 것
			if (!flag) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}

}
