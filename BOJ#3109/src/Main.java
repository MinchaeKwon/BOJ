/**
 * 3109 빵집
 * https://www.acmicpc.net/problem/3109
 * 
 * @author minchae
 * @date 2023. 8. 1.
 * 
 * 그리디 알고리즘
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = { -1, 0, 1 };

    static int R, C;
    static char[][] map; // '.'는 빈 칸, 'x'는 건물

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // dfs 사용 -> 방문처리하고 다시 원복 안해도됨
    private static boolean dfs(int x, int y) {
        // 마지막 열(원웅이의 빵집)에 도착한 경우
        if (y == C - 1) {
            return true;
        }

        // 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순서로 확인 -> 최적의 경로 찾기 위함
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            // 열은 위의 if문에서 확인하므로 행의 범위만 체크
            if (nx >= 0 && nx < R) {
                if (map[nx][ny] == '.') {
                    map[nx][ny] = '-'; // 방문처리 (해당 경로가 성공하든 실패하든 다시 탐색하지 않게 함)

                    // 그리디를 사용하기 때문에 성공하는 것만 확인
                    if (dfs(nx, ny)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }
}