/**
 * 11060 점프 점프
 * https://www.acmicpc.net/problem/11060
 * 
 * @author minchae
 * @date 2023. 7. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
        dp();
    }

    // bfs 사용
    private static void bfs() {
        Queue<int[]> q = new LinkedList<>(); // 인덱스, 점프횟수 저장
        boolean[] visited = new boolean[N + 1];

        q.add(new int[] {0, 0});
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int cnt = cur[1];

            if (x == N - 1) {
                System.out.println(cnt);
                return;
            }

            // 한칸은 무조건 이동하기 때문에 1부터 시작
            for (int i = 1; i <= A[x]; i++) {
                int nx = x + i;

                if (nx < N && !visited[nx]) {
                    q.add(new int[] {nx, cnt + 1});
                    visited[nx] = true;
                }
            }
        }

        System.out.println(-1);
    }

    // dp 사용
    private static void dp() {
        int[] dp = new int[N];

        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= A[i]; j++) {
                // 범위를 벗어나지 않는 경우
                if (i + j < N) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1); // 한칸씩 점프한거랑 비교해서 최솟값 갱신
                }
            }
        }

        System.out.println(dp[N - 1] == Integer.MAX_VALUE - 1 ? -1 : dp[N - 1]);
    }

}