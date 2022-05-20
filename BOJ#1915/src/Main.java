/**
 * 1915 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 * 
 * @author minchae
 * @date 2022. 5. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 배열의 크기가 1 * 1인 경우에는 1 출력하고 종료
		if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }
		
		int[][] dp = new int[n + 1][m + 1];
		int max = 0;
		
		for (int i = 1; i <= n; i++) {
			char[] map = br.readLine().toCharArray();

			for (int j = 1; j <= m; j++) {
				if (i == 1 && j == 1) {
					// 맨 처음(1, 1)에는 입력받은 배열의 숫자 그대로 dp[i][j]에 저장
					dp[i][j] = map[j - 1] - '0';
				} else if (map[j - 1] == '1') {
					// (1, 2) 부터는 왼쪽, 위쪽, 왼쪽 대각선 방향 중 가장 작은 값에 1을 더한 값을 dp[i][j]에 저장
					dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max * max);
	}

}
