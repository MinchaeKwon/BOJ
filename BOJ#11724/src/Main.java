/**
 * 11724 연결 요소의 개수
 * https://www.acmicpc.net/problem/11724
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static LinkedList<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new LinkedList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		visited = new boolean[n + 1];
		int result = 0;
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
//				dfs(i);
				bfs(i);
				result++;
			}
		}
		
		System.out.println(result);
		
	}
	
	// dfs 사용
	public static void dfs(int v) {
		visited[v] = true;
		
		for (int i : list[v]) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}
	
	// bfs 사용
	public static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(v);
		visited[v] = true;
		
		while (!q.isEmpty()) {
			v = q.poll();
			
			for (int i : list[v]) {
				if (!visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}

}
