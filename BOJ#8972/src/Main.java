/**
 * 8972 - 미친 아두이노
 * https://www.acmicpc.net/problem/8972
 * 
 * @author Minchae Gwon
 * @date 2021.2.28
 * 
 * 종수의 아두이노 위치 I
 * 미친 아두이노 위치 R
 * 폭발한 아두이노 위치 X
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
	
	//1, 2, 3, 4, 5, 6, 7, 8, 9 방향
	static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	static int r, c;
	static String[][] map;
	static int[] dir;
	
	static Queue<Point> q = new LinkedList<>(); //미친 아두이노의 위치를 담을 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new String[r][c];
		Point ja = new Point(0, 0); //종수 아두이노의 위치
		
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j) + "";
				
				if (map[i][j].equals("I")) {
					ja = new Point(i, j);
				}
				else if (map[i][j].equals("R")) {
					q.add(new Point(i, j));
				}
			}
		}
		
		String s = br.readLine();
		dir = new int[s.length()];
		
		for (int i = 0; i < dir.length; i++) {
			dir[i] = s.charAt(i) -  '0';
		}
		
		game(ja.x, ja.y);
		
	}
	
	public static void game(int x, int y) {
		for (int i = 0; i < dir.length; i++) {
			/* 새로운 배열을 하나 만들어서 이 배열에 아두이노들이 이동한 위치를 표시함
			 * 아두이노들이 동시에 이동하는 것처럼 하기위해 만드는 것
			 * 실제 이동은 동시에 하지만 코드상으로는 하나씩 움직이고 있기 때문에 새롭게 만들지 않으면 아두이노들이 이동할 때 이상한 곳에서 충돌이 발생함
			 * 이동을 한번씩 할 때마다 초기화 해줌 */
			String[][] cmap = new String[r][c];
			
			//종수의 아두이노를 움직임
			x += dx[dir[i] - 1];
			y += dy[dir[i] - 1];
			
			//종수 아두이노가 미친 아두이노의 위치로 가면 게임 종료
			if (map[x][y].equals("R")) {
				System.out.println("kraj " + (i + 1)); // (i + 1)이 게임이 끝나기 전에 이동한 횟수임
				return;
			}
			
			cmap[x][y] = "I"; //종수 아두이노가 이동한 위치에 I로 표시
			
			while (!q.isEmpty()) {
				Point p = q.poll(); //미친 아두이노의 위치를 꺼냄
				
				int min = Integer.MAX_VALUE;
				Point move = new Point(p.x, p.y);
				
				for (int j = 0; j < 9; j++) {
					//미친 아두이노가 이동할 위치 구함
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					
					if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
						// |r1-r2| + |s1-s2|가 가장 작아지는 방향으로 이동
						int diff = Math.abs(x - nx) + Math.abs(y - ny);
						min = Math.min(min, diff);
						
						if (min == diff) {
							move = new Point(nx, ny);
						}
						
					}
				}
				
				if (cmap[move.x][move.y] != null) {
					//미친 아두이노가 종수 아두이노의 위치로 가면 게임 종료
					if (map[move.x][move.y].equals("I")) {
						System.out.println("kraj " + (i + 1));
						return;
					}
					
					cmap[move.x][move.y] = "X"; //null이 아니고 I도 아니면 이미 R이 있는 경우 -> 충돌이기 때문에 X표시
				}
				else {
					cmap[move.x][move.y] = "R"; //미친 아두이노 이동시킴
				}
				
			}
			
			for (int j = 0;  j < r; j++) {
				for (int k = 0; k < c; k++) {
					if (cmap[j][k] == null || cmap[j][k].equals("X")) {
						map[j][k] = ".";
					}
					else {
						if (cmap[j][k].equals("R")) {
							q.add(new Point(j, k));
						}
						
						map[j][k] = cmap[j][k];
					}
				}
			}
			
		}
		
		//게임 종료 후 보드의 상태 출력
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}

}
