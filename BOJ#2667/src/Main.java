/**
 * 2668 - 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 * 
 * @author Minchae Gwon
 * @date 2021.1.16
 * 
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 * 
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//bfs 할 때 필요한 클래스
class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	//상하좌우의 단지를 확인하기 위한 배열
	static int[] dx = {-1, 1, 0, 0}; //상하
	static int[] dy = {0, 0, -1, 1}; //좌우
	
	//한 단지의 아파트 개수를 세기 위한 변수 -> dfs 함수를 돌면서 아파트 개수를 증가시켜야 하기 때문에 멤버변수로 선언
	static int apart;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] map = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		
		//아파트 단지를 넣을 리스트
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			String m = sc.next();
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(m.substring(j, j + 1));
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					apart = 1; //아파트 단지의 첫 아파트를 발견하면 아파트 개수 증가시킴
					dfs(i, j, visited, map, n); //해당 아파트 주변으로 다른 아파트가 있는지 확인하기 위해 dfs
//					bfs(i, j, visited, map, n);
					list.add(apart);
				}
			}
		}
		
		//아파트 개수 순서로 오름차순으로 정렬
		Collections.sort(list);
		
		//아파트 단지 개수 출력
		System.out.println(list.size());
		
		//각 단지의 아파트를 오름차순으로 출력
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		sc.close();

	}
	
	//dfs로 아파트 단지 탐색 -> 아파트 한 단지를 돌면서 아파트의 개수를 셈
	public static int dfs(int x, int y, boolean[][] visited, int[][] map, int n) {
		visited[x][y] = true;
		
		//해당 아파트 주위로 상하좌우 확인하면서 아파트가 있는지 확인
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			//범위를 벗어나지 않도록 함
			if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
				//상하좌우를 확인하면서 해당 위치에 아파트가 있고 방문하지 않은 아파트일때
				if (map[tx][ty] == 1 && !visited[tx][ty]) {
					dfs(tx, ty, visited, map, n);
					apart++; //한 단지의 아파트 개수 증가시킴
				}
			}
		}
		
		//재귀를 돌면서 상하좌우를 확인하는데 0을 발견할 경우에 return
		return apart;
	}
	
	//bfs로 아파트 단지 탐색
	public static int bfs(int x, int y, boolean[][] visited, int[][] map, int n) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(x, y));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			x = p.x;
			y = p.y;
			
//			x = queue.peek().x;
//			y = queue.poll().y;
			
			for (int i = 0; i < 4; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				
				if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
					//상하좌우를 확인하면서 해당 위치에 아파트가 있고 방문하지 않은 아파트일때
					if (map[tx][ty] == 1 && !visited[tx][ty]) {
						queue.add(new Point(tx, ty)); //아파트가 있고 해당 아파트를 방문하지 않은 상태이면 큐에 넣음
						visited[tx][ty] = true; //방문 표시를 해줌
						apart++;
					}
				}
			}	
		}
		
		//bfs를 하면서 0을 만나면 return
		return apart;
	}

}
