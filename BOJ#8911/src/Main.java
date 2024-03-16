/**
 * 8911 거북이
 * https://www.acmicpc.net/problem/8911
 * 
 * @author minchae
 * @date 2024. 3. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// 상좌하우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static char[] order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while (T-- > 0) {
			order = br.readLine().toCharArray();
			
			// 초기 위치
			int x = 0;
			int y = 0;
			
			// 초기 방향 (북쪽)
			int dir = 0;
			
			int minX = 0;
			int minY = 0;
			int maxX = 0;
			int maxY = 0;
			
			for (char c : order) {
				if (c == 'F') {
					x += dx[dir];
					y += dy[dir];
				} else if (c == 'B') {
					x -= dx[dir];
					y -= dy[dir];
				} else if (c == 'L') {
					dir = (dir + 3) % 4;
				} else {
					dir = (dir + 1) % 4;
				}
				
				minX = Math.min(minX, x);
				minY = Math.min(minY, y);
				maxX = Math.max(maxX, x);
				maxY = Math.max(maxY, y);
			}
			
			sb.append(Math.abs(maxX - minX) * Math.abs(maxY - minY) + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
