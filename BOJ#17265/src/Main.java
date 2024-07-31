/**
 * 17265 나의 인생에는 수학과 함께
 * https://www.acmicpc.net/problem/17265
 * 
 * @author minchae
 * @date 2024. 7. 31.
 */

import java.io.*;
import java.util.*;

public class Main {
	
	// 우, 하
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};

	static int N;
	static char[][] map;
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		dfs(0, 0, map[0][0], map[0][0] - '0');
		
		System.out.println(max + " " + min);
	}
	
	private static void dfs(int x, int y, char op, int num) {
		// 우, 하 방향으로만 움직일 수 있음
		for (int i = 0; i < 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위를 벗어나는 경우 다음으로 넘어감
			if (!isRange(nx, ny)) {
				continue;
			}
			
			// 연산자인 경우해당 연산자를 가지고 dfs
			if (map[nx][ny] == '+' || map[nx][ny] == '-' || map[nx][ny] == '*') {
				dfs(nx, ny, map[nx][ny], num);
			} else { // 숫자인 경우
				int result = 0;
				
				// 이전 문자 확인
				if (op == '+') {
					result = num + (map[nx][ny] - '0');
				} else if (op == '-') {
					result = num - (map[nx][ny] - '0');
				} else if (op == '*') {
					result = num * (map[nx][ny] - '0');
				}
				
				if (nx == N - 1 && ny == N - 1) {
					min = Math.min(min, result);
					max = Math.max(max, result);
					
					return;
				}
				
				dfs(nx, ny, map[nx][ny], result);
			}
			
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
