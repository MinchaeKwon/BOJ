/**
 * 1956 운동
 * https://www.acmicpc.net/problem/1956
 * 
 * @author minchae
 * @date 2024. 3. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 987654321;
	
	static int V, E;
	static int[][] dist;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dist = new int[V + 1][V + 1];
		
		for (int i = 1; i <= V; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dist[a][b] = c;
		}

		floyd();
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	private static void floyd() {
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				// 자기 자신으로 가는 것은 제외
				if (i == j) {
					continue;
				}
				
				// i -> j, j > i 사이클을 발견한 경우 최솟값 갱신
				if (dist[i][j] != INF && dist[j][i] != INF) {
					answer = Math.min(answer, dist[i][j] + dist[j][i]);
				}
			}
		}
	}

}
