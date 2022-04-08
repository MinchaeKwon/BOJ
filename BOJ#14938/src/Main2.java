/**
 * 14938 서강그라운드
 * https://www.acmicpc.net/problem/14938
 * 
 * @author minchae
 * @date 2022. 4. 8.
 * 
 * 플로이드 워샬 이용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	
	static final int INF = 99999999;
	
	static int n, m, r;
	
	static int[] item; // 각 구역에 있는 아이템
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 지역의 개수
		m = Integer.parseInt(st.nextToken()); // 예은이의 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수
		
		item = new int[n + 1];
		map = new int[n + 1][n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					// 아직 비용을 모르는 상태는 INF (자기 자신으로의 경로 비용은 0으로 초기화 됨)
					map[i][j] = INF;
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			// 양방향 통행이 가능하기 때문에 각각 추가해줌
			map[a][b] = l;
			map[b][a] = l;
		}
		
		System.out.println(floyd());
	}
	
	// 플로이드 워샬 이용 메소드
	public static int floyd() {		
		// 각각 모든 경로에 대해 최단 경로를 만들어줌
		for (int k = 1; k <= n; k++) { // k : 경유지
			for (int i = 1; i <= n; i++) { // i : 시작지
				for (int j = 1; j <= n; j++) { // j : 도착지
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int result = 0;
		
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			
			for (int j = 1; j <= n; j++) {
				if (map[i][j] <= m) {
					sum += item[j];
				}
			}
			
			result = Math.max(result, sum);
		}
		
		return result;
	}

}
