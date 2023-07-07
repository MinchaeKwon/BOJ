/**
 * 17136 색종이 붙이기
 * https://www.acmicpc.net/problem/17136
 * 
 * @author minchae
 * @date 2023. 7. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] paper = {5, 5, 5, 5, 5}; // 각 종류의 색종이를 5개씩 가지고 있음
    static int[][] map = new int[10][10];

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        // 1을 모두 덮는 것이 불가능한 경우에는 -1 출력
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // dfs 사용 (백트래킹 이용)
    private static void dfs(int x, int y, int cnt) {
        // 맨 끝에 도착한 경우 최솟값 갱신
        // y > 9를 하는 이유 -> (9, 9)에 도착했을 때 해당 칸에 1이 있을 수 있기 때문 (y >= 9를 하면 (9, 9)에서 바로 종료되어버리기 때문에 올바른 답을 얻을 수 없음)
        if (x >= 9 && y > 9) {
            answer = Math.min(answer, cnt);
            return;
        }

        // 최솟값보다 큰 경우 더 볼 필요 없으므로 탐색 종료
        if (answer <= cnt) {
            return;
        }

        // 열을 끝까지 탐색한 경우 다음 행으로 이동
        if (y > 9) {
            dfs(x + 1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            // 색종이를 덮을 수 있는 경우
            // 색종이 크기가 큰 것부터 확인
            for (int i = 5; i >= 1; i--) {
                if (paper[i - 1] > 0 && check(x, y, i)) {
                    cover(x, y, i, 0); // 색종이 덮음
                    paper[i - 1]--;

                    dfs(x, y + 1, cnt + 1); // 개수 증가시키고 다음 열 탐색

                    cover(x, y, i, 1); // 색종이를 다시 뗌
                    paper[i - 1]++;
                }
                
            }
        } else {
            // 색종이를 덮을 수 없는 경우 다음 열로 이동
            dfs(x, y + 1, cnt);
        }
    }

    // 색종이를 덮을 수 있는지 확인
    private static boolean check(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                // 범위를 벗어나거나, 1이 아닌 경우 (0에는 색종이가 있으면 안됨)
                if (i < 0 || i >= 10 || j < 0 || j >= 10 || map[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // 색종이를 덮음
    private static void cover(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

}