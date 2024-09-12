/**
 * 1937 욕심쟁이 판다
 * https://www.acmicpc.net/problem/1937
 * 
 * @author minchae
 * @date 2024. 9. 13.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int n;
	
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer = Math.max(answer, dfs(i, j)); // 최댓값 갱신
			}
		}
		
		System.out.println(answer);
	}
	
	private static int dfs(int x, int y) {
		// 이미 값이 저장되어 있는 경우 종료
		if (dp[x][y] > 0) {
			return dp[x][y];
		}
		
		dp[x][y] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny)) {
				continue;
			}
			
			// 다음 칸으로 이동 가능한 경우
			if (map[nx][ny] > map[x][y]) {
				// 다음 칸으로 갔을 때의 값과 현재 값과 비교해 최댓값 저장
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
			}
		}
		
		return dp[x][y];
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

}
