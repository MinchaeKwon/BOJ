/**
 * 3967 매직 스타
 * https://www.acmicpc.net/problem/3967
 *  
 * @author minchae
 * @date 2023. 8. 3.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static char[][] map = new char[5][9]; // 수가 채워져 있지 않은 곳은 x, 채워져 있는 곳은 'A'부터 'L'까지 알파벳

    static int cnt = 0; // 'x'의 개수 (알파벳이 들어가야 하는 곳의 개수)
    static boolean[] visited = new boolean[12];
    static ArrayList<int[]> empty = new ArrayList<>(); // 알파벳을 채워야 하는 곳의 위치 저장
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 'x') {
                    cnt++; // 알파벳을 넣을 곳의 개수 증가
                    empty.add(new int[] {i, j});
                } else if (map[i][j] != '.') {
                    visited[map[i][j] - 'A'] = true; // 알파벳인 경우 방문 체크
                }
            }
        }

        backtracking(0, 0);
    }

    // 백트래킹 이용
    private static void backtracking(int idx, int depth) {
        // 'x'의 개수만큼 다 진행하고 각각의 줄의 합이 26인 경우
        if (depth == cnt && checkSum()) {
            for (int i = 0; i < 5; i++) {
                System.out.println(map[i]);
            }

            System.exit(0); // dfs를 이용해서 A ~ L까지 넣어보기 때문에 처음 조건을 만족하는게 사전순 -> 종료
        }

        // 숫자가 들어가는 칸의 개수가 12개
        for (int i = 0; i < 12; i++) {
            // 해당 알파벳이 쓰이지 않은 경우
            if (!visited[i]) {
                int[] cur = empty.get(idx); // empty에 있는 것들 하나씩 넣어봄

                map[cur[0]][cur[1]] = (char) (i + 'A');
                visited[i] = true;

                backtracking(idx + 1, depth + 1);

                map[cur[0]][cur[1]] = 'x';
                visited[i] = false;
            }
        }
    }

    // 각각의 줄에 있는 수의 합이 모두 26인지 확인
    private static boolean checkSum() {
        // 맵에는 0 ~ 11까지의 숫자가 저장되어 있기 때문에 +1을 해서 계산

        // 한줄이라도 26이 되지 않으면 false 반환
        if ((map[0][4] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[3][1] - 'A' + 1) != 26) {
            return false;
        }

        if ((map[0][4] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[3][7] - 'A' + 1) != 26) {
            return false;
        }

        if ((map[1][1] - 'A' + 1) + (map[1][3] - 'A' + 1) + (map[1][5] - 'A' + 1) + (map[1][7] - 'A' + 1) != 26) {
            return false;
        }

        if ((map[3][1] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[3][7] - 'A' + 1) != 26) {
            return false;
        }

        if ((map[4][4] - 'A' + 1) + (map[3][3] - 'A' + 1) + (map[2][2] - 'A' + 1) + (map[1][1] - 'A' + 1) != 26) {
            return false;
        }

        if ((map[4][4] - 'A' + 1) + (map[3][5] - 'A' + 1) + (map[2][6] - 'A' + 1) + (map[1][7] - 'A' + 1) != 26) {
            return false;
        }
     
        return true;
    }
}