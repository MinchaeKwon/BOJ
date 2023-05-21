/**
 * 주사위 굴리기 2
 * https://www.acmicpc.net/problem/23288
 * 
 * @author minchae
 * @date 2023. 5. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    // 동남서북(우하좌상) -> 좌표 계산하기 쉽게 동남서북으로 설정
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static int N, M;
    static int[][] map;

    static int[] dice = { 1, 2, 3, 4, 5, 6 }; // 윗면 1, 뒷면 2, 오른쪽면 3, 왼쪽면 4, 앞면 5, 아랫면 6
    static int cx, cy; // 주사위 현재 위치
    static int dir; // 초기 방향은 동쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        while (K-- > 0) {
            // 1. 주사위를 이동 방향으로 한 칸 이동시킴
            checkAvailable();
            moveDice();

            // 2. 주사위가 도착한 칸에 대한 점수 획득
            result += getScore();

            // 3. 주사위 이동 방향을 결정
            changeDir();
        }

        System.out.println(result);

    }

    // 주사위가 이동 방향으로 한 칸 굴러갈 수 있는지 확인 -> 칸이 없으면 이동 방향을 반대로 해줌
    private static void checkAvailable() {
        int nx = cx + dx[dir];
        int ny = cy + dy[dir];

        // 범위를 벗어나서 이동할 칸이 없는 경우
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
            dir = (dir + 2) % 4; // 이동방향 반대로 전환
        }

        // 현재 위치 변경
        cx += dx[dir];
        cy += dy[dir];
    }

    // 주사위 굴리기
    private static void moveDice() {
        int[] temp = dice.clone();

        // 주사위 굴리기 - 윗면은 1, 뒷면은 2, 오른쪽면은 3, 왼쪽면은 4, 앞면은 5, 아랫면은 6
        switch (dir) {
            case 0: // 동쪽
                dice[0] = temp[3];
                dice[2] = temp[0];
                dice[3] = temp[5];
                dice[5] = temp[2];
                break;
            case 1: // 남쪽
                dice[0] = temp[1];
				dice[1] = temp[5];
				dice[4] = temp[0];
				dice[5] = temp[4];
                break;
            case 2: // 서쪽
                dice[0] = temp[2];
                dice[2] = temp[5];
                dice[3] = temp[0];
                dice[5] = temp[3];
                break;
            case 3: // 북쪽
                dice[0] = temp[4];
                dice[1] = temp[0];
                dice[4] = temp[5];
                dice[5] = temp[1];
                break;
        }

    }

    // 주사위 아랫면에 있는 정수 A와 주사위가 있는 칸에 있는 정수 B를 이용해 이동 방향 결정
    private static void changeDir() {
        if (dice[5] > map[cx][cy]) { // A > B인 경우 90도 시계 방향으로 회전
            dir = (dir + 1) % 4;
        } else if (dice[5] < map[cx][cy]) { // A < B인 경우 90도 반시계 방향으로 회전
            dir = dir == 0 ? 3 : dir - 1;
        }
    }

    // 연속으로 몇 칸 이동할 수 있는지 확인 후에 점수 계산 -> bfs 사용
    private static int getScore() {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Pair(cx, cy));
        visited[cx][cy] = true;

        int cnt = 1;

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[pair.x][pair.y] == map[nx][ny] && !visited[nx][ny]) {
                        q.add(new Pair(nx, ny));
                        visited[nx][ny] = true;

                        cnt++;
                    }
                }
            }
        }

        return map[cx][cy] * cnt;
    }

}