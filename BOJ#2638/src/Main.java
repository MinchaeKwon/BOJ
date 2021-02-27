/**
 * 2638 - 치즈
 * https://www.acmicpc.net/problem/2638
 * 
 * @author Minchae Gwon
 * @date 2021.2.27
 * 
 * 입력
 * 첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5≤N, M≤100)이 주어진다.
 * 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.
 * 
 * 출력
 * 출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.
 * 
 * 외부 공기 -1
 * 맨 처음 공기 영역이나 외부, 내부를 구분하고나서의 내부 공기 0
 * 치즈 1
 * 녹은 치즈 2
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
	
	static int[] dx = {-1, 0, 1, 0}; //상하
	static int[] dy = {0, -1, 0, 1}; //좌우
	
	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		int cheese = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		}
		
		int hour = 0;
		
		//치즈가 없어질때까지 반복
		while (cheese > 0) {
			//치즈를 한 번 녹이면 외부, 내부 공기를 다시 구분해야하므로 다시 bfs 실행
			bfs(0, 0);
			
			//녹은 치즈 탐색
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						int air = 0;
						
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							
							//범위 벗어나지 않고 외부 공기와 접촉하는 경우
							if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == -1) {
								air++;
							}
						}
						
						//외부 공기와 접촉하는 곳이 2개 이상일 경우 치즈가 녹음
						if (air >= 2) {
							//녹은 치즈는 2로 표시
							map[i][j] = 2;
							cheese--; //치즈가 녹았기 때문에 치즈 개수를 --해줌
						}
					}
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					/* 녹은 치즈 영역이거나 외부 공기 영역을 0으로 초기화 해줌
					 * 이렇게 해야 다시 외부 공기와 내부 공기를 구분할 때 문제가 없음(하지 않으면 특정 테스트케이스에서 무한루프에 빠짐)*/
					if (map[i][j] == 2 || map[i][j] == -1) {
						map[i][j] = 0; //녹은 치즈 영역은 0을 넣어줌
					}
				}
			}
			
			hour++;
		}

		System.out.println(hour);
		
	}
	
	//외부 공기, 내부 공기 구분하기 -> bfs 사용
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		
		//맨 왼쪽 위부터 외부 공기 있는지 확인 -> 치즈는 가장자리에 놓이지 않으므로 이 부분은 항상 외부 공기이기 때문에 큐에 넣어줌
		q.add(new Point(x, y));
		
		while (!q.isEmpty()) {
			Point point = q.poll();
			
			x = point.x;
			y = point.y;
		
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				//범위를 벗어나지 않고 공기일 경우 -1로 바꿔주고 큐에 넣어줌
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
					map[nx][ny] = -1;
					q.add(new Point(nx, ny));
				}
			}
			
		}
	}

}
