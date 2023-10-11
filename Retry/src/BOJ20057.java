/**
 * 백준 20057 마법사 상어와 토네이도
 * https://www.acmicpc.net/problem/20057
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
	
	// 좌하우상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	// 토네이도가 이동하는 칸
	static int[] dc = {1, 1, 2, 2};
	
	// 모래가 퍼지는 비율
	static int[] ratio = {1, 1, 2, 7, 7, 2, 10, 10, 5};
	
	// 모래가 퍼지는 x, y 방향
	static int[][] sdx = { 
			{ -1, 1, -2, -1, 1, 2, -1, 1, 0 },	// 좌
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2 },	// 하
			{ -1, 1, -2, -1, 1, 2, -1, 1, 0 },	// 우
			{ 1, 1, 0, 0, 0, 0, -1, -1, -2 } 	// 상
	};

	static int[][] sdy = { 
			{ 1, 1, 0, 0, 0, 0, -1, -1, -2 },	// 좌
			{ -1, 1, -2, -1, 1, 2, -1, 1, 0 },	// 하
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2 },	// 우
			{ 1, -1, 2, 1, -1, -2, 1, -1, 0 } 	// 상
	};
	
	static int N;
	static int[][] map;

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
		
		int result = move(N / 2, N / 2);
		System.out.println(result);

	}
	
	private static int move(int x, int y) {
		int sand = 0;
		
		while (true) {
			// 좌하우상으로 토네이도 이동
			for (int i = 0; i < 4; i++) {
				for (int cnt = 0; cnt < dc[i]; cnt++) {
					// 토네이도가 (0, 0)에 도착해 소멸할 경우 종료
					if (x == 0 && y == 0) {
						return sand;
					}
					
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					int total = map[nx][ny]; // 모래 양
					int sum = 0; // 퍼뜨린 모래의 양
					
					map[nx][ny] = 0;
					
					// 9개의 방향으로 모래 퍼뜨림
					for (int j = 0; j < 9; j++) {
						int sx = nx + sdx[i][j];
						int sy = ny + sdy[i][j];
						
						int spread = (total * ratio[j]) / 100;
						
						if (checkRange(sx, sy)) {
							map[sx][sy] += spread;
						} else {
							sand += spread;
						}
						
						sum += spread;
					}
					
					int alpha = total - sum;
					
					int ax = nx + dx[i];
					int ay = ny + dy[i];
					
					if (checkRange(ax, ay)) {
						map[ax][ay] += alpha;
					} else {
						sand += alpha;
					}
					
					x = nx;
					y = ny;
				}
			}
			
			// 토네이도가 이동하는 칸의 개수 늘림
			for (int i = 0; i < 4; i++) {
				dc[i] += 2;
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
