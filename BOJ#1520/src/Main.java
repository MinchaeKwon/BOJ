/**
 * 1520 내리막 길
 * https://www.acmicpc.net/problem/1520
 * 
 * @author minchae
 * @date 2023. 8. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int M, N;
    static int[][] map;

    static int[][] dp; // 특정 지점에서 도착지까지 가는 경로 개수 저장
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로의 크기 (행)
        N = Integer.parseInt(st.nextToken()); // 가로의 크기 (열)

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // dp 배열 -1로 초기화
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        // -1이 아닌 경우는 이미 방문한 것 -> 탐색할 필요 없음
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0; // 현재 위치에서 도착지까지 가능 경로의 개수 0부터 시작

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                // 다음 지점의 높이가 더 낮은 경우
                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny); // map[nx][ny]에서 도착지까지 가는 경로의 개수 더함
                }
            }
        }

        return dp[x][y];
    }
}