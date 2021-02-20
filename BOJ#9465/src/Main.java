/**
 * 9465 - 스티커
 * https://www.acmicpc.net/problem/9465
 * 
 * @author Minchae Gwon
 * @date 2021.2.20
 * 
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다.
 * 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다. 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다. 
 * 
 * 출력
 * 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N + 1];

            for (int j = 0; j < 2; j++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= N; k++) {
                	sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][N + 1];
            
            //초기값 넣어줌
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            //(0, 1)과 (1, 1)은 이미 구해놓은 상태이므로 2부터 시작
            for (int j = 2; j <= N; j++) {
            	/*
            	 * 예를 들어 현재 (0, 2)라면 (1, 0)과 (1, 1)에서 올 수 있기 때문에 두 경우 중에서 큰 값과 현재 스티커의 합을 구하면 됨
            	 * 이 때 (0, 0)은 이미 (1, 1)에서 계산이 된 상태이기 때문에 고려하지 않음
            	 * 
            	 * 같은 열에서 스티커를 떼어낼 경우 변을 공유하므로 떼어내지 못하므로 다른 열의 대각선에서 뗀 스티커의 점수와 현재 스티커의 점수를 더함
            	 * -> 현재 스티커의 대각선에 있는 스타커의 값이나 그 대각선에 있는 값을 사용하지 않은 경우 대각선의 왼쪽 값을 사용해서 두개의 값 비교
            	 * 
            	 * 왼쪽 대각선(N - 1)과 왼쪽 왼쪽 대각선(N - 2) 중 큰 값과 현재 스티커의 합을 구함
            	*/
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + sticker[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + sticker[1][j];
            }

            //두개의 열중에서 더 큰 점수를 가진 것을 출력
            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
