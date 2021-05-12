/**
 * 17144 미세먼지 안녕!
 * https://www.acmicpc.net/problem/17144
 * 
 * @author minchae
 * @date 2021.5.12.
 * 
 * 공기청정기가 있는 곳은 -1, 나머지는 미세먼지의 양
 * 1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
 * 
 * 출력
 * 첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dust {
	int x;
	int y;
	int amount;
	
	public Dust(int x, int y, int amount) {
		this.x = x;
		this.y = y;
		this.amount = amount;
	}
}

public class Main {
	
	// 상좌하우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int R, C, T;
	static int[][] map;
	
	static int[] airCleaner; // 공기청정기 위치를 저장하는 배열
	static Queue<Dust> queue; // 미세먼지의 위치, 양을 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		airCleaner = new int[2];
		int idx = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 공기청정기일 경우
				if (map[i][j] == -1) {
					airCleaner[idx++] = i;
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			checkDust();
			spreadDust();
			workAirCleaner();
		}
		
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 미세먼지가 있는 경우
				if (map[i][j] != -1 && map[i][j] != 0) {
					result += map[i][j]; // 남아있는 미세먼지를 더함
				}
			}
		}
		
		System.out.println(result);

	}
	
	// 미세먼지가 있는 곳 확인
	public static void checkDust() {
		queue = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 공기청정기가 있는 곳이 아니고, 미세먼지가 없는 곳이 아닌 경우 -> 미세먼지가 있는 경우
				if (map[i][j] != 1 && map[i][j] != 0) {
					queue.add(new Dust(i, j, map[i][j]));
				}
			}
		}
	}
	
	// 미세먼지 확산하기
	public static void spreadDust() {
		while (!queue.isEmpty()) {
			Dust dust = queue.poll();
			
			// 현재 미세먼지의 위치와 양
			int x = dust.x;
			int y = dust.y;
			int amount = dust.amount;
			
			// 확산된 방향의 개수
			int cnt = 0;
			
			// 상하좌우로 미세먼지 확산하기
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// map의 범위를 벗어나지 않고 공기청정기가 없는 경우
				if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
					map[nx][ny] += amount / 5;
					cnt++;
				}
			}
			
			map[x][y] -= (amount / 5) * cnt;
		}
	}
	
	// 공기청정기 작동하기
	public static void workAirCleaner() {
		int top = airCleaner[0]; // 공기청정기 위쪽
		int down = airCleaner[1]; // 공기청정기 아래쪽
		
		// 위쪽 공기청정기의 바람은 반시계방향으로 순환
		for (int i = top - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		for (int i = 0; i < top; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			map[top][i] = map[top][i - 1];
		}
		
		// 공기청정기에서 부는 바람은 미세먼지가 없는 바람이기 때문에 공기청정기 바로 옆은 0을 넣어줌
		map[top][1] = 0;
		
		// 아래쪽 공기청정기의 바람은 시계방향으로 순환
		for (int i = down + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		for (int i = R - 1; i > down; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			map[down][i] = map[down][i - 1];
		}
		
		// 공기청정기에서 부는 바람은 미세먼지가 없는 바람이기 때문에 공기청정기 바로 옆은 0을 넣어줌
		map[down][1] = 0;
	}

}
