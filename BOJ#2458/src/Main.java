/**
 * 2458 키 순서
 * https://www.acmicpc.net/problem/2458
 * 
 * @author minchae
 * @date 2024. 2. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	static final int INF = 999999999;
	
	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			dist[a][b] = 1;
		}
		
		System.out.println(floyd());
	}
	
	private static int floyd() {
		// 경유지 - 출발지 - 도착지 3중 for문
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++)  {
					// 최솟값 갱신
					dist[i][j] = dist[i][j] <= dist[i][k] + dist[k][j] ? dist[i][j] : dist[i][k] + dist[k][j];
				}
			}
		}
		
		int answer = 0;
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			
			for (int j = 1; j <= N; j++)  {
				if (dist[i][j] != INF || dist[j][i] != INF) {
					cnt++;
				}
			}
			
			// 모든 학생과 키를 비교할 수 있는 경우
			if (cnt == N - 1) {
				answer++;
			}
		}
		
		return answer;
	}

}
