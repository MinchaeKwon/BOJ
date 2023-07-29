/**
 * 14620 꽃길
 * https://www.acmicpc.net/problem/14620
 * 
 * @author minchae
 * @date 2023. 7. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;
    
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);
        System.out.println(result);
    }

    // 백트래킹을 통해 꽃을 심음
    private static void backtracking(int depth, int price) {
        if (depth == 3) {
            result = Math.min(result, price);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check(i, j)) {
                    setVisited(i, j, true); // 꽃을 심을 수 있으므로 방문처리 해줌

                    backtracking (depth + 1, price + sumPrice(i, j));

                    setVisited(i, j, false); // 원복
                }
            }
        }
    }

    // 씨앗을 심고 꽃이 만개할 수 있는지 확인
    private static boolean check(int x, int y) {
        if (visited[x][y]) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나고 이미 꽃이 있는 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                return false;
            }
        }

        return true;
    }

    // 방문 여부 값 세팅
    private static void setVisited(int x, int y, boolean value) {
        visited[x][y] = value;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[nx][ny] = value;
        }
    }

    // 화단 비용을 더함
    private static int sumPrice(int x, int y) {
        int sum = map[x][y]; // 씨앗이 있는 자리부터 시작

        // 상하좌우 확인하면서 비용 더해줌
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            sum += map[nx][ny];
        }

        return sum;
    }

}