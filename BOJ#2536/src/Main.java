/**
 * 2536 버스 갈아타기
 * https://www.acmicpc.net/problem/2536
 * 
 * @author minchae
 * @date 2024. 4. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Bus {
		int idx;
		int x1;
		int y1;
		int x2;
		int y2;
		int type; // 수직 0, 수평 1
		
		public Bus(int idx, int x1, int y1, int x2, int y2, int type) {
			this.idx = idx;
			this.x1 = Math.min(x1, x2);
			this.y1 = Math.min(y1, y2);
			this.x2 = Math.max(x1, x2);
			this.y2 = Math.max(y1, y2);
			this.type = type;
		}
		
		// (x, y)가 현재 위치와 만나는지 확인
		public boolean isContain(int x, int y) {
			if (this.type == 0) { // 현재 객체가 수직인 경우
				// x좌표가 같을 때 y좌표가 현재 객체의 y좌표 사이에 있을 경우 경로 겹침
				if (this.x1 == x && y >= this.y1 && y <= this.y2) {
					return true;
				}
			} else {
				if (this.y1 == y && x >= this.x1 && x <= this.x2) {
					return true;
				}
			}
			
			return false;
		}
		
		public boolean isContain(Bus b) {
			if (this.type == 0 && b.type == 0) { // 둘 다 수직인 경우
				if (this.x1 != b.x1 || this.y1 > b.y2 || this.y2 < b.y1) { // 엇갈림
					return false;
				} else {
					return true;
				}
			} else if (this.type == 1 && b.type == 1) { // 둘 다 수평인 경우
				if (this.y1 != b.y1 || this.x1 > b.x2 || this.x2 < b.x1) { // 엇갈림
					return false;
				} else {
					return true;
				}
					
			} else if (this.type == 0 && b.type == 1) { // 하나는 수직이고, 하나는 수평인 경우
				// 크로스되는 경우
				if (this.x1 >= b.x1 && this.x1 <= b.x2 && b.y1 >= this.y1 && b.y1 <= this.y2) {
					return true;
				}
			} else { // 하나는 수평이고, 하나는 수직인 경우
				// 크로스되는 경우
				if (this.y1 >= b.y1 && this.y1 <= b.y2 && b.x1 >= this.x1 && b.x1 <= this.x2) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	static int m, n, k;
	static Bus[] bus;
	
	static int sx, sy, ex, ey;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		bus = new Bus[k + 1];
		
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int b = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if (x1 == x2) {
				bus[b] = new Bus(b, x1, y1, x2, y2, 0); // x좌표가 같을 경우 수직
			} else {
				bus[b] = new Bus(b, x1, y1, x2, y2, 1);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Bus> q = new LinkedList<>();
		int[] visited = new int[k + 1];
		
		for (int i = 1; i <= k; i++) {
			// 시작 위치를 포함하고 있는 경우 큐에 추가
			if (bus[i].isContain(sx, sy)) {
				q.add(bus[i]);
				visited[i] = 1;
			}
		}
		
		while (!q.isEmpty()) {
			Bus cur = q.poll();
			
			if (cur.isContain(ex, ey)) {
				return visited[cur.idx];
			}
			
			for (int i = 1; i <= k; i++) {
				if (visited[i] != 0 || !cur.isContain(bus[i])) {
					continue;
				}
				
				q.add(bus[i]);
				visited[i] = visited[cur.idx] + 1;
			}
		}
		
		return 0;
	}
	
}
