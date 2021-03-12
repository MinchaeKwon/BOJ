/**
 * 1261 알고스팟
 * https://www.acmicpc.net/problem/1261
 * 
 * @author Minchae Gwon
 * 2021.3.12
 * 
 * 0은 빈 방, 1은 벽
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int cnt; // 부순 벽의 개수
	
	public Point(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
	// 우선순위큐를 사용할 때 벽을 부순 개수를 기준으로 정렬하기 위해 사용
	@Override
	public int compareTo(Point o) {
        return cnt - o.cnt;
    }
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N, M;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
	
		System.out.println(bfs(0, 0));
		
	}
	
	public static int bfs(int x, int y) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		
		visited[x][y] = true;
		pq.add(new Point(x, y, 0)); //시작점에서는 벽을 부수지 않았으므로 0을 넣어줌
		
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			x = p.x;
			y = p.y;
			
			// 도착점에 온 경우 -> 최소 경로를 찾는 것이 아니기 때문에 바로 도착점에 온다면 바로 return 해도됨
			if (x == N - 1 && y == M - 1) {
				return p.cnt;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
					int cnt = p.cnt;
					
					// 벽이라면 부순 벽의 개수 증가시킴
					if (map[nx][ny] == 1) {
						cnt++;
					}
					
					visited[nx][ny] = true;
					pq.add(new Point(nx, ny, cnt));
					
				}
			}
		}
		
		return 0;
		
	}

}
