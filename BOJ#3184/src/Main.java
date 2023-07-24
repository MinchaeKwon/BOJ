/**
 * 3184 양
 * https://www.acmicpc.net/problem/3184
 * 
 * @author minchae
 * @date 2023. 7. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int R, C;
    static char[][] map; // '.' (점)은 빈 필드, '#'는 울타리를, 'o'는 양, 'v'는 늑대

    static boolean[][] visited;
    static int sheep, wolf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 아직 방문하지 않았고, 울타리가 아닌 경우 (양, 늑대, 빈 칸인 경우)
                if (!visited[i][j] && map[i][j] != '#') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    // bfs 사용해서 같은 영역 안에 있는 양과 늑대의 수를 계산
    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        visited[x][y] = true;
        q.add(new int[] {x, y});

        int sCnt = 0, wCnt = 0;

        if (map[x][y] == 'o') {
            sCnt++;
        } else if (map[x][y] == 'v') {
            wCnt++;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 벗어나지 않고, 아직 방문하지 않았으며 울타리가 아닌 경우
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && map[nx][ny] != '#') {
                    if (map[nx][ny] == 'o') {
                        sCnt++;
                    } else if (map[nx][ny] == 'v') {
                        wCnt++;
                    }

                    q.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        // 양의 수가 늑대의 수보다 많다면 이기고, 늑대를 우리에서 쫓아냄
        if (sCnt > wCnt) {
            sheep += sCnt;
        } else {
            wolf += wCnt;
        }
    }

}