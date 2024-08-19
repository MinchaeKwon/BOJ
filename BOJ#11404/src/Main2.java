/**
 * 11404 플로이드
 * https://www.acmicpc.net/problem/11404
 * 
 * @author minchae
 * @date 2024. 8. 20.
 */

import java.util.*;
import java.io.*;

public class Main2 {
	
	static final int INF = 987654321;
	
	static int n, m;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					map[i][j] = INF;
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			// 출발 도시와 도착 도시가 같은데 비용이 다른 경우 입력으로 들어올 수 있음 -> 더 작은 값 저장
			map[a][b] = Math.min(map[a][b], c);
		}
		
		floyd();
		print();
	}
	
	private static void floyd() {
		// 각각 모든 경로에 대해 최단 경로 구함
		for (int k = 0; k < n; k++) { // 거쳐가는 도시
			for (int i = 0; i < n; i++) { // 시작 도시
				for (int j = 0; j < n; j++) { // 도착 도시
					// 경유지를 거쳐가는 경우와 그렇지 않은 경우 중에 더 작은 값을 저장
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((map[i][j] == INF ? 0 : map[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
