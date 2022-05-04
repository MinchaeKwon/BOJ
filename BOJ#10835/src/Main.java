/**
 * 10835 카드게임
 * https://www.acmicpc.net/problem/10835
 * 
 * @author minchae
 * @date 2022. 5. 5.
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
		
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);	
		}

		System.out.println(playGame(0, 0));
	}
	
	public static int playGame(int left, int right) {
		// 둘 중에서 남는 카드가 없는 경우 게임 종료
		if (left == N || right == N) {
			return 0;
		}
		
		if (dp[left][right] != -1) {
			return dp[left][right];
		}
		
		// 왼쪽 카드를 버리거나 왼쪽, 오른쪽 카드 두개 다 버리는 경우 중 큰 수 -> 규칙 1
		dp[left][right] = Math.max(playGame(left + 1, right), playGame(left + 1, right + 1));
		
		// 만약 오른쪽 카드가 더 작은 경우에는 오른쪽 카드를 버림(오른쪽 카드 값을 더함) -> 규칙 2
        if (A[left] > B[right])
            dp[left][right] = Math.max(dp[left][right], B[right] + playGame(left, right + 1));
        
        return dp[left][right];
	}

}
