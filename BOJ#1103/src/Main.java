/**
 * 1103 게임
 * https://www.acmicpc.net/problem/1103
 * 
 * @author minchae
 * @date 2023. 8. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static char[][] map;

    static int[][] dp;
    static boolean[][] visited;
    static boolean infinite = false;

    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(infinite ? -1 : answer);
    }

    private static void dfs(int x, int y, int cnt) {
        answer = Math.max(answer, cnt);
        dp[x][y] = cnt;

        for (int i = 0; i < 4; i++) {
            // 동전이 있는 곳에 쓰여 있는 숫자만큼 움직임
            int move = map[x][y] - '0';

            int nx = x + (move * dx[i]);
            int ny = y + (move * dy[i]);

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 'H') {
                // 방문한 곳을 또 방문한 경우에는 무한루프에 빠지게 되므로 종료
                if (visited[nx][ny]) {
                    infinite = true;
                    return;
                }

                // 다음 지점까지 가기 위해 동전을 움직인 횟수와 현재 지점의 게임 횟수보다 큰 경우 -> 진행할 필요 X (다음 판이니까 1을 더해서 같아지는 경우도 어차피 같은 수가 되니까 볼 필요 X)
                if (dp[nx][ny] > cnt) {
                    continue;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1);
                visited[nx][ny] = false;
            }
        }
    }
}