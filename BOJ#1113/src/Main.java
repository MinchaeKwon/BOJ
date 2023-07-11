/**
 * 1113 수영장 만들기
 * https://www.acmicpc.net/problem/1113
 * 
 * @author minchae
 * @date 2023. 7. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int height = 0; // 땅의 최대 높이를 구함

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                height = Math.max(height, map[i][j]);
            }
        }

        int result = 0;

        // 땅의 높이는 1이상이기 때문에 2부터 시작
        for (int h = 2; h <= height; h++) {
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 물은 높이가 더 낮은 곳으로만 흐르고, 직육면체 위에는 물이 없으므로 높이가 작을 경우만 확인
                    if (map[i][j] < h && !visited[i][j]) {
                        result += bfs(new int[] {i, j}, h);
                    }
                    
                }
            }
        }

        System.out.println(result);

    }

    private static int bfs(int[] start, int num) {
        Queue<int[]> q = new LinkedList<>();

        q.add(start);
        visited[start[0]][start[1]] = true;

        int cnt = 1;
        boolean flag = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] < num && !visited[nx][ny]) {
                        q.add(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        cnt++;
                    }
                } else {
                    // 가장자리인 경우에는 물을 부을 수 없음
                    flag = false;
                }
            }
        }

        return flag ? cnt : 0;
    }

}