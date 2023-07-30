/**
 * 2931 가스관
 * https://www.acmicpc.net/problem/2931
 * 
 * @author minchae
 * @date 2023. 7. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int R, C;
    static char[][] map; // 빈칸 '.', 블록 '|'(아스키 124), '-','+','1','2','3','4' / 모스크바의 위치 'M', 자그레브 위치 'Z'
    static int sx, sy; // 시작위치 (모스크바 위치)

    static boolean[][] visited;
    static char[] type = { '|', '-', '+', '1', '2', '3', '4' }; // 가스관 종류

    static int pipeCnt = 0; // 파이프 블록 개수

    static int ex, ey, tx, ty;
    static char pipeType;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M') {
                    sx = i;
                    sy = j;
                }

                if (map[i][j] != 'M' && map[i][j] != 'Z' && map[i][j] != '.') { // 파이프 블록인 경우
                    pipeCnt++;
                }
            }
        }

        pipeCnt++; // 지워진 블록에도 파이프가 있기 때문에 하나 더 증가시킴

        findPipe(sx, sy); // 모스크바에 인접한 파이프 찾음

        System.out.println((ex + 1) + " " + (ey + 1) + " " + pipeType);
    }

    // 모스크바에 인접한 파이프를 기준으로 dfs 실행
    private static void findPipe(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나지 않고, 파이프가 있는 경우
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != '.') {
                dfs(nx, ny, i, true, 0);
            }
        }
    }

    // 가스관을 따라서 dfs 실행 -> 해커가 지운 블록을 찾음
    private static void dfs(int x, int y, int dir, boolean use, int depth) {
        // 모든 파이프 블록만큼 진행한 경우 종료
        if (depth == pipeCnt) {
            ex = tx;
            ey = ty;
            pipeType = map[ex][ey];

            System.out.println((ex + 1) + " " + (ey + 1) + " " + pipeType);

            System.exit(0); // 답을 찾은 경우 종료
        }

        int nd = changeDir(map[x][y], dir); // 파이프 방향에 따른 다음 방향 구함

        if (nd == -1) {
            return;
        }

        int nx = x + dx[nd];
        int ny = y + dy[nd];

        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
            if (map[nx][ny] == '.') {
                if (use) {
                    // 7가지 가스관 다 넣어봄
                    for (int i = 0; i < 7; i++) {
                        tx = nx;
                        ty = ny;

                        map[nx][ny] = type[i];
                        visited[nx][ny] = true;

                        dfs(nx, ny, nd, false, depth + 1);

                        // 원복
                        map[nx][ny] = '.';
                        visited[nx][ny] = false;
                    }
                }
            } else {
                if (visited[nx][ny]) {
                    // '+'인 경우 depth 증가시키면 안됨 -> 수평, 수직 두 방향으로 이동 가능하기 때문
                    dfs(nx, ny, nd, use, depth);
                } else {
                    visited[nx][ny] = true;
                    dfs(nx, ny, nd, use, depth + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    // 방향 바꾸기
    private static int changeDir(char pipe, int dir) {
        if (pipe == '|') {
            if (dir == 0 || dir == 1) return dir;
        } else if (pipe == '-') {
            if (dir == 2 || dir == 3) return dir;
        } else if (pipe == '+') {
            return dir;
        } else if (pipe == '1') {
            if (dir == 2) return 1;
            if (dir == 0) return 3;
        } else if (pipe == '2') {
            if (dir == 1) return 3;
            if (dir == 2) return 0;
        } else if (pipe == '3') {
            if (dir == 3) return 0;
            if (dir == 1) return 2;
        } else if (pipe == '4') {
            if (dir == 3) return 1;
            if (dir == 0) return 2;
        }
        
        return -1;
    }

}