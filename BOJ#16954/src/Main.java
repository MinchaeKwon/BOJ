/**
 * 16954 움직이는 미로 탈출
 * https://www.acmicpc.net/problem/16954
 * 
 * @author minchae
 * @date 2021. 7 . 6.
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

	// 상, 하, 좌, 우, 왼쪽 위, 왼쪽 아래, 오른쪽 위, 오른쪽 아래
	static int[] dx = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
	static int[] dy = {0, -1, 0, 1, 0, -1, -1, 1, 1};
	
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		map = new char[8][8];
		
		for (int i = 0; i <  8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[8][8];
		
		q.add(new Point(7, 0)); // 욱제의 캐릭터 시작 위치 추가
		visited[0][7] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			
			if (x == 0 && y == 7) {
				System.out.println(1);
				return;
			}
			
			for (int i = 0; i < 9; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
					if (!visited[nx][ny] && map[nx][ny] == '.') {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
				
			}
		}
		
	}

}
