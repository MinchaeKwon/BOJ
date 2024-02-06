/**
 * 14503 로봇 청소기
 * https://www.acmicpc.net/problem/14503
 * 
 * @author minchae
 * @date 2024. 2. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 북동남서(상우하좌)
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M;
	static int[][] map; // 청소되지 않은 빈 칸 0, 벽 1, 청소된 칸 2
	
	static int answer = 1; // 로봇 청소기가 있는 칸은 항상 빈 칸이기 때문에 1부터 시작

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(x, y, d);
		
		System.out.println(answer);
	}
	
	private static void clean(int x, int y, int d) {
		map[x][y] = 2; // 청소한 구역은 2로 표시
		
		for (int i = 0; i < 4; i++) {
			d = (d + 3) % 4; // 현재 방향의 다음 방향부터 탐색
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (map[nx][ny] == 0) {
				answer++;
				clean(nx, ny, d);
				
				return; // 여기서 종료해주지 않으면 이전으로 복귀하는 도중에 후진하게 됨
			}
		}
		
		// 바라보는 방향을 유지한 채로 후진
		// 위의 for문에서 현재 방향 다음부터 탐색하기 때문에 빈 칸이 없을 경우 d의 값은 다시 현재 방향 값임
		int bd = (d + 2) % 4; // 현재 방향의 반대 방향으로 후진하기 때문에 (d + 2) % 4를 해줌
		int bx = x + dx[bd];
		int by = y + dy[bd];
		
		if (map[bx][by] != 1) {
			clean(bx, by, d); // 방향을 유지하기 때문에 d를 그대로 넣어줌
		}
	}

}
