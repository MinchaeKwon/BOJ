/**
 * 2169 로봇 조종하기
 * https://www.acmicpc.net/problem/2169
 * 
 * @author minchae
 * @date 2024. 9. 22.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	
	static int[][] map;
	static int[][] dp;
	static int[][] tmp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		tmp = new int[2][M]; // 0에 좌 -> 우, 위 -> 아래 최대, 1에 우 -> 좌, 위 -> 아래 최대 저장
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 세팅
		dp[0][0] = map[0][0];
		
		// 첫 번째 행은 좌 -> 우로만 갈 수 있음
		for (int j = 1; j < M; j++) {
			dp[0][j] = dp[0][j - 1] + map[0][j];
		}
		
		for (int i = 1; i < N; i++) {
			tmp[0][0] = dp[i - 1][0] + map[i][0]; // 위에서 내려온 값 저장
			
			// 좌 -> 우 값이랑 위 -> 아래 값 중 최댓값 찾음
			for (int j = 1; j < M; j++) {
				tmp[0][j] = Math.max(tmp[0][j - 1], dp[i - 1][j]) + map[i][j];
			}
			
			tmp[1][M - 1] = dp[i - 1][M - 1] + map[i][M - 1]; // 위에서 내려온 값 저장
			
			// 우 -> 좌 값이랑 위 -> 아래 값 중 최댓값 찾음
			for (int j = M - 2; j >= 0; j--) {
				tmp[1][j] = Math.max(tmp[1][j + 1], dp[i - 1][j]) + map[i][j];
			}
			
			// 둘 중에서 최댓값 저장
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
			}
		}

		System.out.println(dp[N - 1][M - 1]);
	}

}
