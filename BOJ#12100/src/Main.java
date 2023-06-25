/**
 * 12100 2048 (Easy)
 * https://www.acmicpc.net/problem/12100
 * 
 * @author minchae
 * @date 2023. 6. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N;
    static int[][] map;
    static int[][] copy;
    static boolean[][] visited; // 블록이 이미 합쳐졌는지 확인
    static int[] direction = new int[5]; // 5번의 이동 방향 저장

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(answer);
    }

    // 백트래킹 이용
    private static void dfs(int depth) {
        if (depth == 5) {
            checkBlock();
            return;
        }

        for (int i = 0; i < 4; i++) {
            direction[depth] = i;
            dfs(depth + 1);
        }
    }

    // 블록 5번 이동시켜서 점수 얻기
    private static void checkBlock() {
        copy = new int[N][N]; // 배열 복사해서 사용 -> 백트래킹을 사용하기 때문에 원본 배열을 건드리면 안됨

        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], N);
        }

        // 5번 반복
        for (int d = 0; d < direction.length; d++) {
            visited = new boolean[N][N];
            int dir = direction[d]; // 0 상, 1 하, 2 좌, 3 우

            // 상, 하는 행만 변화하기 때문에 i, j 순서
            // 좌, 우는 열만 변화하기 때문에 j, i 순서
            switch (dir) {
                case 0: // 상
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            moveBlock(i, j, dir);
                        }
                    }

                    break;

                case 1: // 하 -> 밑쪽이니까 (N - 1)부터 확인
                    for (int i = N - 1; i >= 0; i--) {
                        for (int j = 0; j < N; j++) {
                            moveBlock(i, j, dir);
                        }
                    }

                    break;

                case 2: // 좌
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            moveBlock(j, i, dir);
                        }
                    }

                    break;

                case 3: // 우 -> 오른쪽이니까 (N - 1)부터 확인
                    for (int i = N - 1; i >= 0; i--) {
                        for (int j = 0; j < N; j++) {
                            moveBlock(j, i, dir);
                        }
                    }

                    break;
            }

            // 최대 점수 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(answer, copy[i][j]);
                }
            }
        }
    }

    // 블록 이동시키기 -> 이동하려고 하는 쪽의 칸이 먼저 이동
    private static void moveBlock(int x, int y, int dir) {
        // 현재 칸이 빈 칸일 경우 종료
        if (copy[x][y] == 0) {
            return;
        }

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위를 벗어나고, 블록이 이미 합쳐진 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                return;
            }

            if (copy[x][y] == copy[nx][ny]) {
                // 다음 블록과 같은 숫자일 경우 숫자 합치기
                visited[nx][ny] = true;

                copy[nx][ny] *= 2; // 합쳐지니까 2 곱해줌
                copy[x][y] = 0; // 원래 자리는 빈 칸으로 만듦

                return; // 한 번 합쳐졌기 때문에 종료
            } else if (copy[nx][ny] != 0) {
                return; // 서로 다른 숫자일 경우 바로 종료
            }

            // 위의 경우에 해당하지 않을 경우 다음 칸으로 이동 시킴 (같은 숫자인데 이미 합쳐졌거나 빈 칸일 경우)
            copy[nx][ny] = copy[x][y];
            copy[x][y] = 0;

            x = nx;
            y = ny;
        }
    }

}