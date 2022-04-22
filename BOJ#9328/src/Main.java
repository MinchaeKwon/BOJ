/**
 * 9328 열쇠
 * https://www.acmicpc.net/problem/9328
 * 
 * @author minchae
 * @date 2022. 4. 22.
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

	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			
			for (int j = 0; j < h; j++) {
				map[j] = br.readLine().toCharArray();
			}
		}

	}
	
	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		
		while (!q.isEmpty()) {
			
		}
	}

}
