/**
 * 1149 RGB 거리
 * https://www.acmicpc.net/problem/1149
 * 
 * @author minchae
 * @date 2021.3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[N][3];
		
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			cost[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			cost[i][1] = Integer.parseInt(st.nextToken()); // 초록
			cost[i][2] = Integer.parseInt(st.nextToken()); // 파랑 
		}
		
		for (int i = 1; i < N; i++) {
			// (i - 1)을 특정 색을 제외한 나머지 색으로 칠한 경우 중에 더 작은 값을 더해야 최소값을 구할 수 있음
			cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
			cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
			cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
		}
		
		System.out.println(Math.min(cost[N - 1][0], Math.min(cost[N - 1][1], cost[N - 1][2])));

	}

}
