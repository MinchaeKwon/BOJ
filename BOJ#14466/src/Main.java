/**
 * 14466 소가 길을 건너간 이유 6
 * https://www.acmicpc.net/problem/14466
 * 
 * @author Minchae Gwon
 * @date 2021.3.16
 * 
 * 소가 있는 곳은 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static int[] dx = {-1, 0, 1, 0}; // 상하
	static int[] dy = {0, -1, 0, 1}; // 좌우
	
	static int N, K, R;
	static int[][] map;
	static ArrayList<Point>[][] list;
	static ArrayList<Point> cow = new ArrayList<>(); // 소의 위치를 저장할 리스트
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		list = new ArrayList[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			
			list[r1][c1].add(new Point(r2, c2));
			list[r2][c2].add(new Point(r1, c1));
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			map[a][b] = 1; // 소가 있는 위치 저장
			cow.add(new Point(a, b));
		}
		
		int result = 0;
		
		for (int i = 0; i < K; i++) {
			bfs(cow.get(i).x, cow.get(i).y);
			
			// 다 탐색하고 와서 만날 수 없는 소가 몇 쌍인지 알아냄
			// j가 (i + 1)부터 시작하는 이유 -> i번째 소는 (i - 1)번째 소가 다른 소들과 길을 건너지 않고 만날 수 있는지 탐색할 때 이미 탐색이 다 되었기 때문
			for (int j = i + 1; j < K; j++) {
				// 소가 있는 위치에 방문하지 않은 경우 -> 길을 건너지 않으면 만날 수 없음
				if (!visited[cow.get(j).x][cow.get(j).y]) {
					result++;
				}
			}
		}
		
		System.out.println(result);
		
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			x = p.x;
			y = p.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				boolean flag = true;
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					for (Point road : list[x][y]) {
						// 길이라면 큐에 추가되지 않도록 flag = false로 바꿔주고 for문을 나오게 continue를 사용함
						if (road.x == nx && road.y == ny) {
							flag = false;
							continue;
						}
					}
					
					// 길이 아닌 경우에만 큐에 추가 -> 길을 건너지 않고 만날 수 있는 경우에만 추가함
					if (flag) {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
					
				}
				
			}
			
		}
		
	}

}
