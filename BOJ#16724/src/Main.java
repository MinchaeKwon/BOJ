/**
 * 16724 피리 부는 사나이
 * https://www.acmicpc.net/problem/16724
 * 
 * @author minchae
 * @date 2024. 4. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	static int[][] visited;
	
	static int num, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			
			Arrays.fill(visited[i], -1);
			
			for (int j = 0; j < M; j++) {
				int c = s.charAt(j);
				
				if (c == 'U') {
					map[i][j] = 0;
				} else if (c == 'D') {
					map[i][j] = 1;
				} else if (c == 'L') {
					map[i][j] = 2;
				} else {
					map[i][j] = 3;
				}
			}
		}
		
		answer = 0;
		num = 0; // 그룹 구분

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == -1) {
					num++;
					dfs(i, j);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	// 사이클 확인
	private static void dfs(int x, int y) {
		visited[x][y] = num;
		
		// 격자를 벗어나는 방향을 없기 때문에 범위 확인은 안 해도 됨
		int nx = x + dx[map[x][y]];
		int ny = y + dy[map[x][y]];
		
		if (visited[nx][ny] == -1) {
			dfs(nx, ny);
		} else if (visited[nx][ny] == num) {
			// // 이미 방문한 곳인 경우 사이클이 발생한 것이기 때문에 개수 증가
			answer++;
		}
	}

}
