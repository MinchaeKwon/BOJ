/**
 * 12100 2048 (Easy)
 * https://www.acmicpc.net/problem/12100
 * 
 * @author minchae
 * @date 2024. 2. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12100 {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	
	static int[] direction = new int[5]; // 5번 이동할 때의 방향 저장
	
	static int[][] copy;
	static boolean[][] visited; // 블록이 이미 합쳐졌는지 확인
	
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int depth) {
		if (depth == 5) {
			getScore();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			direction[depth] = i;
			dfs(depth + 1);
		}
	}
	
	// 5번 이동시켜서 점수 최댓값 얻기
	private static void getScore() {
		copy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], N);
		}
		
		for (int d = 0; d < 5; d++) {
			int dir = direction[d];
			
			visited = new boolean[N][N];
			
			// 상, 하 -> 열마다 확인
			// 좌, 우 -> 행마다 확인
			switch (dir) {
			case 0: // 상
				// 맨 위의 열부터 확인
				for (int j = 0; j < N; j++) {
					for (int i = 0; i < N; i++) {
						moveBlock(i, j, dir);
					}
				}
				
				break;
			case 1: // 하
				// 맨 밑의 열부터 확인
				for (int j = 0; j < N; j++) {
					for (int i = N - 1; i >= 0; i--) {
						moveBlock(i, j, dir);
					}
				}
				
				break;
			case 2: // 좌
				// 맨 왼쪽 행부터 확인
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						moveBlock(i, j, dir);
					}
				}
				
				break;
			case 3: // 우
				// 맨 오른쪽 행부터 확인
				for (int i = 0; i < N; i++) {
					for (int j = N - 1; j >= 0; j--) {
						moveBlock(i, j, dir);
					}
				}
				
				break;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, copy[i][j]);
			}
		}
	}
	
	// 특정 방향으로 블록 이동시키기
	private static void moveBlock(int x, int y, int dir) {
		// 현재 칸이 빈 칸인 경우 넘어감
		if (copy[x][y] == 0) {
			return;
		}
		
		// 해당 방향에 빈 칸이 없을 때까지 이동
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 범위를 벗어나거나 이미 숫자가 합쳐진 칸인 경우
			if (!isRange(nx, ny) || visited[nx][ny]) {
				return;
			}
			
			// 숫자가 있는 경우 (빈 칸이 아닌 경우)
			if (copy[nx][ny] > 0) {
				// 이동하려는 칸에 같은 숫자가 있는 경우
				if (copy[nx][ny] == copy[x][y]) {
					copy[nx][ny] *= 2;
					copy[x][y] = 0;
					
					visited[nx][ny] = true;
				}
				
				return;
			}
			
			// 빈 칸인 경우 이동 시킴
			copy[nx][ny] = copy[x][y];
			copy[x][y] = 0;
			
			x = nx;
			y = ny;
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0&& y < N;
	}
	
}
