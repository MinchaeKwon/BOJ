/**
 * 1941 소문난 칠공주
 * https://www.acmicpc.net/problem/1941
 * 
 * @author minchae
 * @date 2023. 7. 17.
 * 
 * 조합 참고 - https://minhamina.tistory.com/38
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static char[][] map = new char[5][5]; // 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)

    // 0 ~ 24좌표를 미리 계산해줌
    static int[] combX = new int[25];
    static int[] combY = new int[25];

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 25; i++) {
            combX[i] = i % 5;
            combY[i] = i / 5;
        }

        comb(new int[7], 0, 0, 7);

        System.out.println(answer);
    }

    // 1. 7명 뽑는거 조합 이용 (재귀 사용)
    private static void comb(int[] arr, int index, int depth, int r) {
        if (r == 0) {
            // 7명 다 뽑은 경우 bfs를 통해 서로 인접해 있는지 확인
            bfs(arr);
            return;
        }

        if (depth == 25) {
            return;
        }

        arr[index] = depth;
        comb(arr, index + 1, depth + 1, r - 1); // 칠공주 뽑는 경우
        comb(arr, index, depth + 1, r); // 뽑지 않는 경우
    }

    // 2. 7명의 자리가 서로 인접해 있는지 확인하는건 bfs 사용, 이다솜파가 4명이상 있는지 확인
    private static void bfs(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[7];

        q.add(arr[0]);

        int cnt = 1; // 인접한 공주들의 수
        int sCnt = 0; // 이다솜파의 수

        while (!q.isEmpty()) {
            int cur = q.poll();

            int x = combX[cur];
            int y = combY[cur];

            // 이다솜파 학생 수 증가
            if (map[x][y] == 'S') {
                sCnt++;
            }

            // 7명이 서로 인접해 있는지 확인
            for (int i = 0; i < 4; i++) {
                int nx  = x + dx[i];
                int ny = y + dy[i];

                // 7명 중 a를 기준으로 a를 제외한 나머지가 a에 인접해 있는지 확인 -> 인접해 있는 학생을 큐에 넣고 방문처리 하면 해당 학생으로 기준으로 또 인접한 학생을 찾게 됨
                for (int k = 1; k < 7; k++) {
                    if (!visited[k] && nx == combX[arr[k]] && ny == combY[arr[k]]) {
                        q.add(arr[k]);
                        visited[k] = true;

                        cnt++;
                    }
                }
            }
        }

        // 7명 모두 인접해 있고, 그 중 이다솜파의 수가 4명이상인 경우
        if (cnt == 7 && sCnt >= 4) {
            answer++;
        }

    }
    
}