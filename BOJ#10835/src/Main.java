/**
 * 10835 카드게임
 * https://www.acmicpc.net/problem/10835
 * 
 * @author minchae
 * @date 2022. 5. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] A, B;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		B = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);	
		}

	}
	
	public static int playGame(int left, int right) {
		// 둘 중에서 남는 카드가 없는 경우 게임 종료
		if (left == N || right == N) {
			return 0;
		}
		
		return -1;
	}

}
