/**
 * 14502 연구소
 * https://www.acmicpc.net/problem/14502
 * 
 * @author Minchae Gwon
 * @date 2021.3.10
 * 
 * 0은 빈 칸, 1은 벽, 2는 바이러스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N, M;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.println(result);
		
	}
	
	// 벽 3개 세우기
	public static void dfs(int depth) {
		if (depth == 3) { // 벽을 3개 다 세운 경우
			bfs(); // 바이러스 퍼뜨리기
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(depth + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	// 바이러스 퍼뜨리고 안전 영역 구하기
	public static void bfs() {
		int[][] virus = new int[N][M];
		Queue<Point> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 벽을 3개 세울 때 재귀를 사용하므로 복사하지 않으면 변경된 맵을 계속 사용해서 결과가 제대로 나오지 않기 때문에 새로운 맵을 만들어서 해야 결과가 제대로 나옴 
				virus[i][j] = map[i][j];
				
				// 바이러스를 큐에 넣어줌
				if (virus[i][j] == 2) {
					q.add(new Point(i, j));
				}
			}
		}
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위를 벗어나지 않고 빈 칸일 경우 바이러스를 퍼뜨리고 큐에 넣어줌
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && virus[nx][ny] == 0) {
					virus[nx][ny] = 2;
					q.add(new Point(nx, ny));
				}
			}
		}
		
		// 바이러스를 다 퍼뜨린 후에 안전 영역 구하기
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virus[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		result = Math.max(result, cnt);		
	}

}
