/**
 * 2210 숫자판 점프
 * https://www.acmicpc.net/problem/2210
 * 
 * @author minchae
 * @date 2023. 7. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static String[][] map = new String[5][5];
    static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                map[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, map[i][j]);
            }
        }

        System.out.println(result.size());
    }

    private static void dfs(int x, int y, int depth, String num) {
        // 숫자 6개 다 뽑은 경우 (depth가 0부터 시작하기 때문에 5가 되면 종료)
        if (depth == 5) {
            // 이미 만들어진 숫자가 아닐 경우 result에 추가
            if (!result.contains(num)) {
                result.add(num);
            }

            return;
        }

        // 특정 칸에서 상하좌우 탐색하면서 숫자 붙임
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 숫자가 중복으로 들어갈 수 있기 때문에 방문처리는 따로 하지 않음 (범위만 확인)
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                dfs(nx, ny, depth + 1, num + map[nx][ny]);
            }
        }
    }
    
}