/**
 * 13901 로봇
 * https://www.acmicpc.net/problem/13901
 * 
 * @author minchae
 * @date 2023. 7. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Robot {
    int x;
    int y;

    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int R, C;
    static int[][] map;
    static int sr, sc; // 로봇의 시작 위치
    static int[] dir = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int k = Integer.parseInt(br.readLine()); // 장애물의 개수

        // 각 장애물의 위치
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // 장애물이 있는 곳은 1
        }

        st = new StringTokenizer(br.readLine());

        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        // 이동 방향의 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            dir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        simulation(0); // 사용자가 지정한 방향 중 첫 번째 방향부터 시작하므로 0을 넣어줌

        // 로봇의 마지막 위치 출력
        System.out.println(sr + " " + sc);
    }

    private static void simulation(int d) {
        map[sr][sc] = 1;
        int cnt = 0;

        while (true) {
            int nr = sr + dx[dir[d]];
            int nc = sc + dy[dir[d]];

            // 범위를 벗어나지 않고 빈곳인 경우 (장애물이 아니고 로봇이 지나간 위치 아닐 경우)
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 0) {
                map[nr][nc] = 1;
                sr = nr;
                sc = nc;

                cnt = 0;
            } else {
                d = (d + 1) % 4; // 방향 전환
                cnt++; // 로봇이 이동할 수 없어서 방향을 전환하는 경우 cnt 증가
            }

            // 사용자가 지정한 방향을 모두 갈 수 없는 경우 로봇이 움직일 수 없음 -> 동작 멈춤
            if (cnt == 4) {
                break;
            }
        }

    }

}