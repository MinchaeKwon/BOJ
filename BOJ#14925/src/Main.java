/**
 * 14925 목장 건설하기
 * https://www.acmicpc.net/problem/14925
 * 
 * @author minchae
 * @date 2023. 8. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[M + 1][N + 1];
        int result = 0;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int input = Integer.parseInt(st.nextToken());

                if (input == 0) {
                    // 특정 칸에서 정사각형이 만들어지기위해 (i - 1, j), (i - 1, j - 1), (i, j - 1) 세 개를 보면 정사각형을 만들 수 있음
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1; // 최솟값 찾으면 
                    result = Math.max(result, dp[i][j]); // 정사각형 크기 최댓값 갱신
                }
            }
        }

        System.out.println(result);
    }
}