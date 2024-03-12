/**
 * 10159 저울
 * https://www.acmicpc.net/problem/10159
 * 
 * @author minchae
 * @date 2024. 3. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			map[i][i] = 1;
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			map[n1][n2] = 1; // n1 > n2 -> 1 저장
			map[n2][n1] = -1; // n1 < n2 -> -1 저장
		}
		
		floyd();
		print();
	}
	
	private static void floyd() {
		// 경유지 - 출발지 - 도착지 3중 for문
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					}
					
					if (map[i][k] == -1 && map[k][j] == -1) {
						map[i][j] = -1;
					}
				}
			}
		}
	}
	
	private static void print() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
			
			sb.append(cnt + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
