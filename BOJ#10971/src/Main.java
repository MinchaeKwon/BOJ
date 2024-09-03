/**
 * 10971 외판원 순회 2
 * https://www.acmicpc.net/problem/10971
 * 
 * @author minchae
 * @date 2024. 4. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[] visited;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 도시에서 출발하는 경우를 구함
//		for (int i = 0; i < N; i++) {
//			visited[i] = true;
//			dfs(i, i, 0, 0);
//			visited[i] = false;
//		}
		
		/*
		 * 출발 도시가 다르다고 최소 비용이 달라지지 않음
		 * 어떤 도시에서 출발하든지 모든 도시를 순회한 후 다시 출발했던 도시로 돌아오는 과정에서 사이클이 발생함
		 * 그래서 최소 비용은 같음
		 * */
		visited[0] = true;
		dfs(0, 1, 0, 0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int start, int depth, int cur, int sum) {
		// 모든 도시를 다 순회한 경우
		if (depth == N) {
			if (map[cur][start] > 0) {
				answer = Math.min(answer, sum + map[cur][start]); // 시작점으로 가는 비용 추가
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// 아직 방문하지 않았고, 연결된 도시인 경우
			if (!visited[i] && map[cur][i] > 0) {
				visited[i] = true;
				dfs(start, depth + 1, i, sum + map[cur][i]);
				visited[i] = false;
			}
		}
	}

}
