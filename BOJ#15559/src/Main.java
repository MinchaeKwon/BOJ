/**
 * 15559 내 선물을 받아줘
 * https://www.acmicpc.net/problem/15559
 * 
 * @author minchae
 * @date 2024. 10. 14.
 * 
 * dfs 사용 -> 사이클이 되는 경우가 선물을 놓을 수 있는 경우
 * */

import java.io.*;
import java.util.*;

public class Main {
	
	// 상좌우하
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	static int N, M;
	
	static int[][] map;
	static int[][] visited;
	
	static int depth;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			
			for (int j = 0; j < M; j++) {
				char c = input.charAt(j);
				
				if (c == 'N') {
					map[i][j] = 0;
				} else if (c == 'W') {
					map[i][j] = 1;
				} else if (c == 'E') {
					map[i][j] = 2;
				} else {
					map[i][j] = 3;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == 0) {
					depth++;
					move(i, j);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void move(int x, int y) {
		visited[x][y] = depth;
		
		// 다음 위치 구하기
		int nx = x + dx[map[x][y]];
		int ny = y + dy[map[x][y]];
		
		if (visited[nx][ny] == 0) {
			move(nx, ny);
		} else if (visited[nx][ny] == depth) { // 사이클이 생긴 경우 선물 개수 증가
			answer++;
		}
	}
	
}