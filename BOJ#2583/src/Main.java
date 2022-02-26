/**
 * 2583 영역 구하기
 * https://www.acmicpc.net/problem/2583
 * 
 * @author minchae
 * @date 2022. 2. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

	// 상좌하우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int M, N;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[M][N];
	
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int lx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			
			int rx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			
			for (int j = ly; j < ry; j++) {
				for (int k = lx; k < rx; k++) {
					map[j][k] = 1;
				}
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 0) {
					result.add(bfs(i, j));
				}
			}
		}
		
		System.out.println(result.size());
		
		Collections.sort(result); // 오름차순 정렬
		for (int count : result) {
			System.out.print(count + " ");
		}
	}
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		int cnt = 1; // 직사각형 내부가 아닌 경우에만 bfs를 하기 때문에 1을 넣어줌
		
		while(!q.isEmpty()) {
			Point point = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				
				if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
					// 아직 방문하지 않았고, 직사각형 내부가 아닌 경우
					if (!visited[nx][ny] && map[nx][ny] == 0) {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
						cnt++;	
					}
				}
			}
		}
		
		return cnt;
	}

}
