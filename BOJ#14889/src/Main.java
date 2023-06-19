/**
 * 14889 스타트와 링크
 * https://www.acmicpc.net/problem/14889
 * 
 * @author minchae
 * @date 2023. 6. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;

    static boolean[] visited;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    // 백트래킹을 통해 팀을 나눔 (조합 사용)
    private static void dfs(int start, int depth) {
        // 팀이 모두 뽑힌 경우
        if (depth == N / 2) {
            answer = Math.min(answer, getAbilityDiff());
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    // 나눠진 팀의 능력치 계산 후 차이 구하고 최솟값 갱신
    private static int getAbilityDiff() {
        int startTeam = 0; // visited가 true
        int linkTeam = 0; // visited가 false

        // 대각선을 기준으로 대칭이기 때문에 오른쪽만 탐색
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) { // 스타트 팀일 경우
                    startTeam += map[i][j] + map[j][i];
                } else if (!visited[i] && !visited[j]) { // 링크 팀일 경우
                    linkTeam += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(startTeam - linkTeam);
    }
    
}