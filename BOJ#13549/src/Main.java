/**
 * 13549 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 * 
 * @author minchae
 * @date 2021.3.26
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int n;
	int time;
	
	public Pair(int n, int time) {
		this.n = n;
		this.time = time;
	}
}

public class Main {
	
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치 
		int K = Integer.parseInt(st.nextToken()); // 동생 위치 
		
		visited = new boolean[100001];
		
		bfs(N, K);
		
		
	}
	
	public static void bfs(int N, int K) {
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(N, 0));
		visited[N] = true;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			
			// 동생을 찾는 경우 종료 
			if (p.n == K) {
				System.out.println(p.time);
				return;
			}
			
			// n의 범위가 0 ~ 100000이기 때문에 이를 벗어나지 않아야함 
			// 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동
			if (2 * p.n <= 100000 && !visited[2 * p.n]) {
				q.add(new Pair(2 * p.n, p.time));
				visited[2 * p.n] = true;
			}
			
			// 걷는다면 1초 후에 X-1로 이동
			if (p.n - 1 >= 0 && !visited[p.n - 1]) {
				q.add(new Pair(p.n - 1, p.time + 1));
				visited[p.n - 1] = true;
			}
			
			// 걷는다면 1초 후에 X+1로 이동
			if (p.n + 1 <= 100000 && !visited[p.n + 1]) {
				q.add(new Pair(p.n + 1, p.time + 1));
				visited[p.n + 1] = true;
			}
			
		}
	}

}
