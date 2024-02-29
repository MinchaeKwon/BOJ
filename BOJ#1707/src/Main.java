/**
 * 1707 이분 그래프
 * https://www.acmicpc.net/problem/1707
 * 
 * @author minchae
 * @date 2024. 2. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] list;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[V + 1];
			visited = new int[V + 1];
			
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				list[u].add(v);
				list[v].add(u);
			}
			
			boolean result = true;
			
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0) {
					result = bfs(i);
					
					if (!result) {
						break;
					}
				}
			}
			
			System.out.println(result ? "YES" : "NO");
		}

	}
	
	private static boolean bfs(int idx) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(idx);
		visited[idx] = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int n : list[cur]) {
				if (visited[n] == 0) {
					q.add(n);
				}
				
				// 서로 인접한 경우 이분 그래프가 될 수 없음
				if (visited[n] == visited[cur]) {
					return false;
				}
				
				if (visited[cur] == 1) {
					visited[n] = 2;
				} else if (visited[cur] == 2) {
					visited[n] = 1;
				}
			}
		}
		
		return true;
	}

}
