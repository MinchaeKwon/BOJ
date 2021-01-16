/**
 * 2178 - 미로 탐색
 * https://www.acmicpc.net/problem/2178
 * 
 * @author minch
 * @date 2021.1.16
 * 
 * 문제
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
 * 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
 * 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 * 
 * 입력
 * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
 * 
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	//상하좌우의 길을 확인하기 위한 배열
	static int[] dx = {-1, 1, 0, 0}; //상하
	static int[] dy = {0, 0, -1, 1}; //좌우

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String m = sc.next();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(m.substring(j, j + 1));
			}
		}
		
		//최단 거리 구할때는 bfs를 사용해야함 -> dfs를 사용하면 잘못된 길로 계속 들어갈 수 있기 때문(최단 거리가 구해지지도 않음)
		bfs(0, 0, visited, map, N, M);
		
		//(N, M)위치에 도착할 때 이동한 최소 칸 수는는 map[N - 1][M - 1]에 저장되어 있음(인덱스가 0부터 시작하기 때문)
		System.out.println(map[N - 1][M - 1]);
		
		sc.close();

	}
	
	public static void bfs(int x, int y, boolean[][] visited, int[][] map, int n, int m) {
		Queue<int[]> q = new LinkedList<>();
		
		visited[x][y] = true;
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] point = q.poll();
			x = point[0];
			y = point[1];
			
			for (int i = 0; i < 4; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				
				if (tx >= 0 && tx < n && ty >= 0 && ty < m) {
					if (map[tx][ty] == 1 && !visited[tx][ty]) {
						q.add(new int[] {tx, ty});
						visited[tx][ty] = true;
						map[tx][ty] = map[x][y] + 1; //방문한 좌표에 이동한 칸 횟수를 저장함 -> (N, M)까지 이동할 때의 최소 칸 수를 알 수 있음
					}
				}
			}
		}
	}

}
