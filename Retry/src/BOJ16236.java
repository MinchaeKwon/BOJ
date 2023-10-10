/**
 * 백준 16236 아기 상어
 * https://www.acmicpc.net/problem/16236
 * 
 * @author minchae
 * @date 2023. 10. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int d;
	
	public Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public int compareTo(Point o) {
		if (this.d == o.d) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			
			return this.x - o.x;
		}
		
		return this.d - o.d;
	}
}
	 
public class BOJ16236 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static int[][] map;
	
	static int sx, sy;
	
	static int size = 2; // 아기 상어의 크기
	static int eat; // 먹은 물고기의 수
	static int time; // 물고기를 잡아먹은 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 9) {
					sx = i;
					sy = j;
					
					map[i][j] = 0; // 아기 상어가 있는 자리는 지나갈 수 있는 곳이기 때문에 0으로 바꿔줌
				}
				
			}
		}
		
		boolean move = true;
		
		while (move) {
			move = moveShark();
			
			if (!move) {
				break;
			}
			
			// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
			if (eat == size) {
				size++;
				eat = 0; // 먹은 물고기 수 초기화
			}
		}
		
		System.out.println(time);

	}
	
	private static boolean moveShark() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		
		pq.add(new Point(sx, sy, 0));
		visited[sx][sy] = true;
		
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			
			// 아기 상어보다 크기가 작은 물고기인 경우
			if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
				time += cur.d;
				eat++;
				map[cur.x][cur.y] = 0;
				
				sx = cur.x;
				sy = cur.y;
				
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나지 않고, 방문하지 않았으며 지나갈 수 있는 칸인 경우
				if (checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] <= size) {
					pq.add(new Point(nx, ny, cur.d + 1));
					visited[nx][ny] = true;
				}
			}
					
		}
		
		return false;
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
