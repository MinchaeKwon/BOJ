/**
 * 17143 낚시왕
 * https://www.acmicpc.net/problem/17143
 * 
 * @author minchae
 * @date 2023. 6. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
    int x;
    int y;
    int speed;
    int dir;
    int size;

    public Shark(int x, int y, int speed, int dir, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }
}

public class Main {

    // 상하우좌
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static int R, C, M;
    static Shark[][] map;

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()) - 1; // 이동방향 (1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽)
            int z = Integer.parseInt(st.nextToken()); // 크기

            map[r][c] = new Shark(r, c, s, d, z);
        }

        // 1. 낚시왕이 오른쪽으로 이동 -> 열의 번호 증가시키면서 이동
        for (int c = 0; c < C; c++) {
            fishing(c);
            moveShark();
        }

        System.out.println(answer);
    }

    // 2. 땅과 가장 가까운 상어 잡음
    private static void fishing(int c) {
        for (int r = 0; r < R; r++) {
            // 열에 있는 상어 중에서 땅과 제일 가까운 상어 찾음(행의 번호가 작은 것을 찾으면 됨)
            if (map[r][c] != null) {
                answer += map[r][c].size; // 상어 크기 더함
                map[r][c] = null; // 잡힌 상어는 격자판에서 사라짐
                return;
            }
        }
    }

    // 3. 상어 이동 -> 주어진 속도로 이동하는데 격자판을 벗어나는 경우 방향을 반대로 바꾸고 다시 이동
    private static void moveShark() {
        
        // 1. 새로운 맵을 만들고 상어를 이동시키면서 확인
        Shark[][] newMap = new Shark[R][C]; // 상어가 모두 이동한 후에 상어 크기를 비교해야 하기 때문에 새로운 맵을 만듦

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                Shark s = map[r][c];

                if (s == null) {
                    continue;
                }

                // 상어 발견하면 속력만큼 이동시킴
                int speed = s.speed;
                speed %= (s.dir < 2 ? R - 1 : C - 1) * 2;

                for (int i = 0; i < speed; i++) {
                    int nx = s.x + dx[s.dir];
                    int ny = s.y + dy[s.dir];

                    // 이동할 위치가 경계를 벗어나는 경우
                     if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        s.x -= dx[s.dir];
                        s.y -= dy[s.dir];
                        s.dir = s.dir % 2 == 0 ? s.dir + 1 : s.dir - 1; // 방향 반대로 바꿈

                        continue;
                    }

                    // 상어 위치 변경
                    s.x = nx;
                    s.y = ny;
                }

                // 4. 이동 후에 한 칸에 두 마리 이상의 상어가 있을 경우 크기가 큰 상어가 나머지 상어를 잡아먹음
                if (newMap[s.x][s.y] == null || newMap[s.x][s.y].size < s.size) {
                    newMap[s.x][s.y] = s; // 현재 이동한 칸에 상어가 없어나, 이미 있는 상어보다 크기가 큰 경우 맵에 넣어줌
                }
            }
        }
        
        map = newMap; // 이동 완료한 맵을 원래 맵에 덮어씌움

        // 2. 큐에 상어들을 먼저 넣어놓은 다음 맵에 삽입
        // Queue<Shark> q = new LinkedList<>();

        // for (int i = 0; i < R; i++) {
        //     for (int j = 0; j < C; j++) {
        //         if (map[i][j] != null) {
        //             q.add(new Shark(i, j, map[i][j].speed, map[i][j].dir, map[i][j].size));
        //         }
        //     }
        // }

        // map = new Shark[R][C]; // 맵 초기화 (새로운 격자판 만들기 위함)

        // while (!q.isEmpty()) {
        //     Shark s = q.poll();

        //     int speed = s.speed;
        //     speed %= (s.dir < 2 ? R - 1 : C - 1) * 2;

        //     for (int i = 0; i < speed; i++) {
        //         int nx = s.x + dx[s.dir];
        //         int ny = s.y + dy[s.dir];

        //         if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
        //             // 이전 위치로 복구
        //             s.x -= dx[s.dir];
        //             s.y -= dy[s.dir];

        //             s.dir = s.dir % 2 == 0 ? s.dir + 1 : s.dir - 1; // 방향 반대로 바꿈

        //             continue;
        //         }

        //         s.x = nx;
        //         s.y = ny;
        //     }

        //     if (map[s.x][s.y] != null && map[s.x][s.y].size > s.size) { // 이미 상어가 있다면 두 상어 크기 비교
        //         continue;
        //     }

        //     map[s.x][s.y] = s;

        //     // if (map[s.x][s.y] == null || map[s.x][s.y].size < s.size) {
        //     //     map[s.x][s.y] = s;
        //     // }
        // }
    }

}