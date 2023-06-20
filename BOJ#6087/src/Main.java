/**
 * 6087 레이저 통신
 * https://www.acmicpc.net/problem/6087
 * 
 * @author Minchae Gwon
 * @date 2021.3.11
 * 
 * 빈 칸은 '.', 벽은 '*', C: 레이저로 연결해야 하는 칸
 * 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int dir;
	int cnt; // 거울 개수
	
	public Point(int x, int y, int dir, int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}

	// 거울 개수를 기준으로 오름차순 정렬
    	@Override
	public int compareTo(Point o) {
		return cnt - o.cnt;
	}
}

public class BOJ6087 {
	
	// 상좌하우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int W, H;
	static char[][] map;
	static ArrayList<Point> laser = new ArrayList<>(); // 레이저의 위치를 담을 리스트
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
            		map[i] = s.toCharArray();
			
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'C') {
					laser.add(new Point(i, j, -1, 0)); // 레이저를 리스트에 추가, 레이저는 방향이 없으므로 -1을 넣어줌
				}
			}
		}
		
		// 레이저 시작 부분부터 탐색 시작
		bfs(laser.get(0));
		System.out.println(result);
		
	}
	
	public static void bfs(Point start) {
	        PriorityQueue<Point> q = new PriorityQueue<>();
		int[][][] mirror = new int[4][H][W]; // 레이저 방향, 특정 위치까지 필요한 거울의 최소 개수 저장 
			
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < H; j++) {
	                Arrays.fill(mirror[i][j], Integer.MAX_VALUE);
	            }
	        }
		
		// 레이저 끝 부분
		Point end = laser.get(1);
		
		q.add(start);
		
		while (!q.isEmpty()) {
	            Point p = q.poll();
	
	            int x = p.x;
	            int y = p.y;
	            int dir = p.dir;
	
	            if (x == end.x && y == end.y) {
	                result = Math.min(result, p.cnt);
	                continue;
	            }

	            for (int i = 0; i < 4; i++) {
	                int nx = x + dx[i];
	                int ny = y + dy[i];
	                
	                if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] != '*') {
				// 현재 좌표와 다음 좌표의 방향이 다른 경우와 -1이 아닌 경우에만 거울 개수 증가 (-1은 레이저가 있는 부분이기 때문에 거울 설치할 필요 없음)
				int cnt = dir == i || dir == -1 ? p.cnt : p.cnt + 1;
	                    
				// 현재 위치에서의 거울 개수가 더 작을 경우에만 큐에 추가 -> 다 추가해버리면 이미 들어갔던게 또 큐에 들어갈 수 있음
				if (mirror[i][nx][ny] > cnt) {
					mirror[i][nx][ny] = cnt;
					q.add(new Point(nx, ny, i, cnt));
				}
	                }
	            }
		}
		
	}

}
