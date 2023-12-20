/**
 * 16918 봄버맨
 * https://www.acmicpc.net/problem/16918
 * 
 * @author minchae
 * @date 2023. 12. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int R, C, N;
	static char[][] map;
	
	static int[][] time; // 폭탄을 설치한 시간
	static Queue<int[]> q = new LinkedList<>(); // 터뜨릴 폭탄 위치 기억하기 위해 사용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		time = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O') {
					time[i][j] = 2;
				}
			}
		}
		
		if (N % 2 == 0) { // N이 짝수인 경우 마지막에는 다 폭탄으로 채워짐
			for (int i = 0; i < R; i++) {
				Arrays.fill(map[i], 'O');
			}
		} else {
			for (int t = 1; t <= N; t++) {
				decrease();
				
				if (t % 2 == 0) {
					install();
				} else {
					bomb();
				}
			}
		}
		
		// N초가 지난 후의 격자판 상태를 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	// 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
	private static void install() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.') {
					map[i][j] = 'O';
					time[i][j] = 2;
				}
			}
		}
	}
	
	// 3초 전에 설치된 폭탄이 모두 폭발
	private static void bomb() {
		// 틀린 방법
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				if (map[i][j] == 'O' && time[i][j] == 0) {
//					map[i][j] = '.';
//					
//					for (int d = 0; d < 4; d++) {
//						int nx = i + dx[d];
//						int ny = j + dy[d];
//						
//						// 터뜨릴 폭탄이 연속으로 존재하는 경우 해당 if문에는 들어가지 않게 돼서 옆에 있는 폭탄의 인접한 4방향은 폭발하지 않게 됨
//						if (isRange(nx, ny) && map[nx][ny] == 'O') {
//							map[nx][ny] = '.';
//							time[nx][ny] = 0;
//						}
//					}
//				}
//			}
//		}
		
		// 폭탄 위치 기억 -> 위치를 기억하고 있다면 연속된 폭탄이 있는 경우에도 폭발이 올바르게 일어남
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O' && time[i][j] == 0) {
					q.add(new int[] {i, j});
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			int x = cur[0];
			int y = cur[1];
			
			map[x][y] = '.';
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (isRange(nx, ny) && map[nx][ny] == 'O') {
					map[nx][ny] = '.';
					time[nx][ny] = 0;
				}
			}
		}
	}
	
	private static void decrease() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (time[i][j] > 0) {
					time[i][j]--;
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
