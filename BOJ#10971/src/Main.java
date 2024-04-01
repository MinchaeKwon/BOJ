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
		dfs(0, 0, 0, 0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int start, int now, int depth, int sum) {
		// 다시 시작점으로 돌아와야 하는데 visited로 인해 시작점은 다시 방문하지 않기 때문에 현재 노드에서 시작점까지의 비용을 더해줌
		if (depth == N - 1) {
			if (map[now][start] > 0) {
				answer = Math.min(answer, sum + map[now][start]);
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i] && map[now][i] > 0) {
				visited[i] = true;
				dfs(start, i, depth + 1, sum + map[now][i]);
				visited[i] = false;
			}
		}
	}

}
