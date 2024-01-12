/**
 * 3015 오아시스 재결합
 * https://www.acmicpc.net/problem/3015
 * 
 * @author minchae
 * @date 2024. 1. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Pair {
	int height;
	int cnt; // 특정 키를 가진 사람의 수를 저장
	
	public Pair(int height, int cnt) {
		this.height = height;
		this.cnt = cnt;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Pair> stack = new Stack<>();
		
		long answer = 0;
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			Pair cur = new Pair(n, 1);
			
			// 새로 들어온 키가 스택 top의 키보다 큰 경우 
			while (!stack.isEmpty() && stack.peek().height <= n) {
				Pair pop = stack.pop();
				
				answer += pop.cnt; // 스택에서 pop 했으니까 개수 더해줌
				
				// 만약 키가 똑같은 경우 개수 증가시킴
				if (pop.height == n) {
					cur.cnt += pop.cnt;
				}
			}
			
			// 자신보다 키가 작은 사람들을 다 pop 했는데 스택에 값이 남아있는 경우 그 사람도 볼 수 있기 때문에 값 증가
			if (!stack.isEmpty()) {
				answer++;
			}
			
			stack.push(cur); // 자기 자신을 스택에 넣어줌
		}
		
		System.out.println(answer);

	}

}
