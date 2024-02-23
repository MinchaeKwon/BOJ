/**
 * 20057 마법사 상어와 토네이도
 * https://www.acmicpc.net/problem/20057
 * 
 * @author minchae
 * @date 2024. 2. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057_2 {
	
	// 좌하우상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int[] dc = {1, 1, 2, 2}; // 토네이도가 이동하는 칸
	
	static int[] ratio = {1, 1, 2, 7, 7, 2, 10, 10, 5}; //모래가 퍼지는 비율
	
	// 모래가 퍼지는 방향
	static int[][] sdx = {
			{-1, 1, -2, -1, 1, 2, -1, 1, 0},
			{-1, -1, 0, 0, 0, 0, 1, 1, 2},
			{-1, 1, -2, -1, 1, 2, -1, 1, 0},
			{1, 1, 0, 0, 0, 0, -1, -1, -2}
	};
	
	static int[][] sdy = {
		{1, 1, 0, 0, 0, 0, -1, -1, -2},
		{-1, 1, -2, -1, 1, 2, -1, 1, 0},
		{-1, -1, 0, 0, 0, 0, 1, 1, 2},
		{1, -1, 2, 1, -1, -2, 1, -1, 0}
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
		
		move(N / 2, N / 2);
	}
	
	private static void move(int x, int y) {
		int answer = 0; // 격자 밖으로 나간 모래의 양
		
		while (true) {
			for (int i = 0; i < 4; i++) {
				for (int c = 0; c < dc[i]; c++) {
					if (x == 0 && y == 0) {
						System.out.println(answer);
						return;
					}
					
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					int total = map[nx][ny];
					int sum = 0; // 퍼뜨린 모래의 양
					
					map[nx][ny] = 0;
					
					// 9개의 방향으로 모래 퍼뜨림
					for (int j = 0; j < 9; j++) {
						int sx = nx + sdx[i][j];
						int sy = ny + sdy[i][j];
						
						int spread = (total * ratio[j]) / 100;
						
						if (isRange(sx, sy)) {
							map[sx][sy] += spread;
						} else {
							answer += spread;
						}
						
						sum += spread;
					}
					
					// 9개의 방향으로 퍼뜨리고 남은 모래를 α로 퍼뜨림
					int alpha = total - sum;
					
					int ax = nx + dx[i];
					int ay = ny + dy[i];
					
					if (isRange(ax, ay)) {
						map[ax][ay] += alpha;
					} else {
						answer += alpha;
					}
					
					// 토네이도 위치 갱신
					x = nx;
					y = ny;;
				}
			}
			
			// 토네이도가 이동하는 칸 개수 증가시킴
			for (int i = 0; i < 4; i++) {
				dc[i] += 2;
			}
		}
		
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
