/**
 * 12851 숨바꼭질 2
 * https://www.acmicpc.net/problem/12851
 * 
 * @author minchae
 * @date 2021.3.29
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
	
	static int min = Integer.MAX_VALUE;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		bfs(N, K);

		System.out.println(min);
		System.out.println(cnt);
	}
	
	public static void bfs(int N, int K) {
		boolean[] visited = new boolean[100001];
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(N, 0));
		visited[N] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			// 특정 정점을 한 번 더 방문할 수 있게 하기 위해 큐에 push 할 때 방문처리를 하는 것이 아니라 pop 할 때 방문처리 함
			visited[p.n] = true;
			
			if(p.n == K) {
				if(p.time <= min) {
					min = p.time;
					cnt++;
				}
			}
		
			if(2 * p.n <= 100000 && !visited[2 * p.n]) {
				q.add(new Pair(2 * p.n, p.time + 1));
			}	
			
			if(p.n - 1 >= 0 && !visited[p.n - 1]) {
				q.add(new Pair(p.n - 1, p.time + 1));
			}
			
			if(p.n + 1 <= 100000 && !visited[p.n + 1]) {
				q.add(new Pair(p.n + 1, p.time + 1));
			}

		}
	}

}
