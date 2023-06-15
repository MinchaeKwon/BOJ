/**
 * 17837 새로운 게임
 * https://www.acmicpc.net/problem/17780
 * 
 * @author minchae
 * @date 2023. 6. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Horse {
    int x;
    int y;
    int dir;

    public Horse(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {

    // 우좌상하
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int N, K;
    static int[][] color;
    static Horse[] horses;
    static ArrayList<Integer>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        color = new int[N][N];
        horses = new Horse[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken()); // 0은 흰색, 1은 빨간색, 2는 파란색
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1; // 1부터 순서대로 →, ←, ↑, ↓ (우좌상하)

            horses[i] = new Horse(x, y, dir);
            map[x][y].add(i);
        }

        int answer = 0;

        outer: while (answer++ < 1000) {
            for (int i = 0; i < K; i++) {
                Horse node = horses[i];

                int x = node.x;
                int y = node.y;

                // 현재 이동하려는 말이 가장 아래에 있는지 확인 (가장 아래에 있는 말만 이동할 수 있기 때문)
                if (map[x][y].get(0) != i) {
                    continue;
                }

                // 이동하려는 칸
                int nx = node.x + dx[node.dir];
                int ny = node.y + dy[node.dir];

                // 범위를 벗어나거나 파란색 칸일 경우
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
                    // 원위치
                    nx -= dx[node.dir];
                    ny -= dy[node.dir];

                    int dir = (node.dir % 2 == 0 ? node.dir + 1 : node.dir - 1); // 변경하려는 방향

                    // 변경한 방향으로 이동
                    nx += dx[dir];
                    ny += dy[dir];

                    node.dir = dir; // 방향 변경

                    // 이동하려는 칸도 범위를 벗어나거나 파란색 칸일 경우
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
                        continue;
                    } else {
                        moveHorse(color[nx][ny], nx, ny, map[x][y]);
                    }
                } else {
                    moveHorse(color[nx][ny], nx, ny, map[x][y]);
                }

                map[x][y].clear(); // 이동한 후에 원래 위치에 있던 말들은 삭제

                // 한칸에 말이 4개 이상 쌓이는 순간 게임 종료
                if (map[nx][ny].size() >= 4) {
                    break outer;
                }
            }

        }

        System.out.println(answer > 1000 ? -1 : answer);
    }

    // 말 이동
    private static void moveHorse(int type, int x, int y, ArrayList<Integer> list) {
        if (type == 0) {
            // 흰색 칸인 경우 - 해당 칸으로 이동
            for (Integer num : list) {
                map[x][y].add(num);

                horses[num].x = x;
                horses[num].y = y;
            }
        } else if (type == 1) {
            // 빨간색 칸인 경우 - 해당 칸으로 이동 후에 해당 말을 포함한 위의 말들의 순서를 바꿈
            for (int i = list.size() - 1; i >= 0; i--) {
                map[x][y].add(list.get(i));

                // 해당 말을 포함한 말들이 같이 움직이기 때문에 다 x, y 넣어줌
                horses[list.get(i)].x = x;
                horses[list.get(i)].y = y;
            }
        }
    }
}