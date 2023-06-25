/**
 * 14501 퇴사
 * https://www.acmicpc.net/problem/14501
 * 
 * @author minchae
 * @date 2023. 6. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] T, P;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        T = new int[N]; // 상담 완료 기간
        P = new int[N]; // 상담 금액

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // dfs
        calPrice1(0, 0);
        System.out.println(result);

        // dp
        calPrice2();
    }

    // dfs 사용
    private static void calPrice1(int idx, int pay) {
        // 퇴사하는 날인 경우 최댓값 계산 후 종료
        if (idx == N) {
            result = Math.max(result, pay);
            return;
        }

        // 상담 날짜가 퇴사하는 날을 넘어갈 경우 종료
        if (idx > N) {
            return;
        }

        // 일을 하게 되는 경우 -> 날짜와 상담 금액 증가
        calPrice1(idx + T[idx], pay + P[idx]);

        // 일을 하지 않는 경우 -> 날짜만 증가
        calPrice1(idx + 1, pay);
    }

    // dp 사용
    private static void calPrice2() {
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            if (i + T[i] <= N) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]); // 오늘 + 상담완료 날짜에 저장된 금액 / 오늘 이전까지의 금액 + 오늘 금액 비교
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]); // 오늘까지의 금액 -> 이전까지 일했던 금액의 최댓값으로 갱신
        }

        System.out.println(dp[N]);
    }
}