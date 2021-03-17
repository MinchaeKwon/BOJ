/**
 * 9019 DSLR
 * https://www.acmicpc.net/problem/9019
 * 
 * @author minchae
 * @date 2021 3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Register {
	int num;
	String command;
	
	public Register(int num, String command) {
		this.num = num;
		this.command = command;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			bfs(A, B);
			
		}

	}
	
	public static void bfs(int start, int end) {
		Queue<Register> q = new LinkedList<>();
		boolean[] visited = new boolean[10000]; // A와 B가 10000만이기 때문에 크기를 10000으로 해줌 
		
		q.add(new Register(start, "")); // 처음에는 명령이 없는 상태이기 때문에 ""를 넣어줌
		visited[start] = true;
		
		while (!q.isEmpty()) {
			Register r = q.poll();
			
			if (r.num == end) {
				System.out.println(r.command);
				return;
			}
			
			int D = r.num * 2 % 10000;
			int S = r.num == 0 ? r.num - 1 : 9999;
			int L = r.num % 1000 * 10 + r.num / 1000;
			int R = r.num % 10 * 1000 + r.num / 10;
			
			if (!visited[D]) {
				q.add(new Register(D, r.command + "D"));
				visited[D] = true;
			}
			
			if (!visited[S]) {
				q.add(new Register(S, r.command + "S"));
				visited[S] = true;
			}
			
			if (!visited[L]) {
				q.add(new Register(L, r.command + "L"));
				visited[L] = true;
			}
			
			if (!visited[R]) {
				q.add(new Register(R, r.command + "R"));
				visited[R] = true;
			}
			
			
		}
		
	}

}
