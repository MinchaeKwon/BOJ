/**
 * 14890 경사로
 * https://www.acmicpc.net/problem/14890
 * 
 * @author minchae
 * @date 2023. 6. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            // 행 확인
            if (checkPath(0, i, 0)) {
                answer++;
            }
            
            // 열 확인
            if (checkPath(1, 0, i)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // 1. 길을 확인하는 방향은 행, 열 두 가지 -> type이 0이면 행, 1이면 열 확인
    private static boolean checkPath(int type, int x, int y) {
        boolean[] visited = new boolean[N]; // 경사로가 설치되었는지 확인
        int[] height = new int[N]; // 각 행 또는 열의 높이 저장

        for (int i = 0; i < N; i++) {
            height[i] = type == 0 ? map[x][y + i] : map[x + i][y];
        }

        for (int i = 0; i < N - 1; i++) {
            int diff = height[i] - height[i + 1];

            // 높이 차이가 1을 초과할 경우 길을 지나갈 수 없음
            if (Math.abs(diff) > 1) {
                return false;
            }

            // 경사로를 L만큼 낮은 칸에 놓아야함

            if (diff == 1) { // 내려가는 경우
                // 현재 칸의 다음 칸부터 이후의 칸을 L만큼 검사(다음 칸이 한 칸 낮으니까 다음 칸부터 L만큼 확인하는 것)
                for (int j = i + 1; j <= i + L; j++) {
                    // 범위를 벗어나거나, 이미 경사로가 놓여있거나, 높이 차이가 나는 경우(L개가 연속되어 놓을 수 없는 경우)
                    if (j >= N || visited[j] || height[i + 1] != height[j]) {
                        return false;
                    }

                    visited[j] = true;
                }
            } else if (diff == -1) { // 올라가는 경우
                // 현재 칸 포함해서 이전 칸을 L만큼 검사(다음 칸이 한 칸 높으니까 현재 칸 포함해서 L만큼 이전 칸들을 보는 것)
                for (int j = i; j > i - L; j--) {
                    // 범위를 벗어나거나, 이미 경사로가 놓여있거나, 높이 차이가 나는 경우(L개가 연속되어 놓을 수 없는 경우)
                    if (j < 0 || visited[j] || height[i] != height[j]) { 
                        return false;
                    }
                    
                    visited[j] = true;
                }
            }
        }

        return true;
    }

}