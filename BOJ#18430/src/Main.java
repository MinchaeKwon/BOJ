/**
 * 18430 무기공학
 * https://www.acmicpc.net/problem/18430
 * 
 * @author minchae
 * @date 2024. 3. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(0, 0);
		
		System.out.println(answer);
	}
	
	private static void backtracking(int depth, int sum) {
		if (depth == N * M) {
			answer = Math.max(answer, sum);
			return;
		}
		
		int x = depth / M;
		int y = depth % M;
		
		if (!visited[x][y]) {
			if (x + 1 < N && y - 1 >= 0 && !visited[x + 1][y] && !visited[x][y - 1]) {
				visited[x + 1][y] = true;
				visited[x][y] = true;
				visited[x][y - 1] = true;
				
				backtracking(depth + 1, sum + map[x + 1][y] + map[x][y - 1] + (map[x][y] * 2));
				
				visited[x + 1][y] = false;
				visited[x][y] = false;
				visited[x][y - 1] = false;
			}
			
			if (x - 1 >= 0 && y + 1 < M && !visited[x - 1][y] && !visited[x][y + 1]) {
				visited[x - 1][y] = true;
				visited[x][y] = true;
				visited[x][y + 1] = true;
				
				backtracking(depth + 1, sum + map[x - 1][y] + map[x][y + 1] + (map[x][y] * 2));
				
				visited[x - 1][y] = false;
				visited[x][y] = false;
				visited[x][y + 1] = false;
			}
			
			if (x + 1 < N && y + 1 < M && !visited[x + 1][y] && !visited[x][y + 1]) {
				visited[x + 1][y] = true;
				visited[x][y] = true;
				visited[x][y + 1] = true;
				
				backtracking(depth + 1, sum + map[x + 1][y] + map[x][y + 1] + (map[x][y] * 2));
				
				visited[x + 1][y] = false;
				visited[x][y] = false;
				visited[x][y + 1] = false;
			}
			
			if (x - 1 >= 0 && y - 1 >= 0 && !visited[x - 1][y] && !visited[x][y - 1]) {
				visited[x - 1][y] = true;
				visited[x][y] = true;
				visited[x][y - 1] = true;
				
				backtracking(depth + 1, sum + map[x - 1][y] + map[x][y - 1] + (map[x][y] * 2));
				
				visited[x - 1][y] = false;
				visited[x][y] = false;
				visited[x][y - 1] = false;
			}
		}
		
		backtracking(depth + 1, sum); // 방문하지 않는 경우도 체크
	}
	
}
