/**
 * 13913 숨바꼭질 4
 * https://www.acmicpc.net/problem/13913
 * 
 * @author minchae
 * @date 2021. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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
	static int[] path = new int[100001]; // 경로 저장하기 위한 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		bfs(N, K);
		
		Stack<Integer> stack = new Stack<>();
		int idx = K;
		while (idx != N) {
			stack.push(idx);
			idx = path[idx];
		}
		stack.push(N);

		System.out.println(min);
		
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
	}
	
	public static void bfs(int N, int K) {
		boolean[] visited = new boolean[100001];
		path = new int[100001];
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(N, 0));
		visited[N] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			if(p.n == K) {
				if(p.time <= min) {
					min = p.time;
					return;
				}
			}
		
			if(2 * p.n <= 100000 && !visited[2 * p.n]) {
				q.add(new Pair(2 * p.n, p.time + 1));
				visited[p.n * 2] = true;
				path[p.n * 2] = p.n;
			}	
			
			if(p.n - 1 >= 0 && !visited[p.n - 1]) {
				q.add(new Pair(p.n - 1, p.time + 1));
				visited[p.n - 1] = true;
				path[p.n - 1] = p.n;
			}
			
			if(p.n + 1 <= 100000 && !visited[p.n + 1]) {
				q.add(new Pair(p.n + 1, p.time + 1));
				visited[p.n + 1] = true;
				path[p.n + 1] = p.n;
			}

		}
	}

}
