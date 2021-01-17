/**
 * 1012 - 유기농 배추
 * https://www.acmicpc.net/problem/1012
 * 
 * @author Minchae Gwon
 * @date 2021.1.16
 * 
 * 입력
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 
 * 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다.
 * 
 * 출력
 * 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	//상하좌우의 지렁이를 확인하기 위한 배열
	static int[] dx = {-1, 1, 0, 0}; //상하
	static int[] dy = {0, 0, -1, 1}; //좌우
	
	static int earthworm;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //테스트 케이스 개수
		
		for (int i = 0; i < T; i++) {
			int M = sc.nextInt(); //가로길이
			int N = sc.nextInt(); //세로길이
			int K = sc.nextInt(); //배추가 심어져 있는 위치의 개수
			
			int[][] map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for (int j = 0; j < K; j++) {
				//배추의 위치
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				map[y][x] = 1; //(세로, 가로)로 저장해야 배추 위치가 올바르게 저장됨
			}
			
			earthworm = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[j][k] == 1 && !visited[j][k]) {
						dfs(j, k, visited, map, N, M);
//						bfs(j, k, visited, map, N, M);
						earthworm++; //배추 한 군데를 확인했으니 지렁이 한마리를 증가시킴
					}
				}
			}
			
			System.out.println(earthworm);
		}
		
		sc.close();

	}
	
	//dfs로 배추 구역 탐색
	public static void dfs(int r, int c, boolean[][] visited, int[][] map, int n, int m) {
		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int tr = r + dx[i];
			int tc = c + dy[i];
			
			if (tr >= 0 && tr < n && tc >= 0 && tc < m) {
				//상하좌우를 확인하면서 해당 위치에 배추가 있고 방문하지 않은 배추일때
				if (map[tr][tc] == 1 && !visited[tr][tc]) {
					dfs(tr, tc, visited, map, n, m);
				}
			}
		}
	}
	
	//bfs로 배추 구역 탐색
	public static void bfs(int r, int c, boolean[][] visited, int[][] map, int n, int m) {
		//좌표를 저장하기 위해 int배열을 타입으로 함
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			int[] point = q.poll();
			r = point[0];
			c = point[1];
			
			for (int i = 0; i < 4; i++) {
				int tc = c + dx[i];
				int tr = r + dy[i];
				
				if (tr >= 0 && tr < n && tc >= 0 && tc < m) {
					if (map[tr][tc] == 1 && !visited[tr][tc]) {
						q.add(new int[] {tr, tc});
						visited[tr][tc] = true;
					}	
				}
			}
		}
		
	}

}
