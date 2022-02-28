/**
 * 9205 맥주 마시면서 걸어가기
 * https://www.acmicpc.net/problem/9205
 * 
 * @author minchae
 * @date 2022. 2. 28.
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

	static int n;
	static boolean[] visited;
	static Point[] point;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			
			point = new Point[n + 2];
			visited = new boolean[n + 2];
			
			for (int j = 0; j < n + 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				point[j] = new Point(x, y);
			}
			
			if (bfs()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}

	}
	
	public static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		
		q.add(point[0]); // 시작 위치 추가 (상근이네 집)
		visited[0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			// 페스티벌 위치에 도착했다면 종료
			if (cur.equals(point[n + 1])) {
				return true;
			}
			
			// point[0]을 처음 넣었기 때문에 i는 1부터 시작
			for (int i = 1; i < n + 2; i++) {
				// 아직 방문하지 않았고, 맥주를 마시면서 이동할 수 있는 거리(50 * 20)인 경우
				if (!visited[i] && Math.abs(cur.x - point[i].x)  + Math.abs(cur.y - point[i].y) <= 1000) {
					q.add(point[i]);
					visited[i] = true;
				}
			}
		}
		
		return false;
	}

}
