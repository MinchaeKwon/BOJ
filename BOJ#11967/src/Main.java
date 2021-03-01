/**
 * 11967 불켜기
 * https://www.acmicpc.net/problem/11967
 * 
 * @author Minchae Gwon
 * @date 2021.3.1
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
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int n;
	static ArrayList<Point>[][] list; //스위치 정보 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			list[x][y].add(new Point(a, b));
		}
		
		System.out.println(light());

	}
	
	public static int light() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][n]; //방문했는지 확인
		boolean[][] isLight = new boolean[n][n]; //불이 켜져있는지 확인
		boolean[][] isMove = new boolean[n][n]; //이동 가능한지 확인
		
		// (1, 1)은 불이 켜져있으므로 true
		visited[0][0] = isLight[0][0] = true;
		q.add(new Point(0, 0));
		
		int result = 1; //(1, 1)은 불이 켜져 있는 상태이기 때문에 result는 1부터 시작
		
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (Point p : list[x][y]) {
				//불이 꺼져있다면 불 켜줌
				if (!isLight[p.x][p.y]) {
					isLight[p.x][p.y] = true;
					result++;
				}
			}
			
			//이동할 수 있는 장소인지 확인
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					isMove[nx][ny] = true;
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//불이 켜져있고 이동할 수 있으며 방문하지 않았을 경우에 해당 위치를 큐에 넣어줌
					if (isMove[i][j] && isLight[i][j] && !visited[i][j]) {
						visited[i][j] = true;
						q.add(new Point(i, j));
					}
				}
			}
			
			
		}
		
		return result;
	}

}
