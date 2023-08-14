/**
 * 11559 Puyo Puyo
 * https://www.acmicpc.net/problem/11559
 * 
 * @author minchae
 * @date 2023. 8. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static char[][] map = new char[12][6]; // . 빈공간, R 빨강, G 초록, B 파랑, P 보라, Y 노랑

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;

        // 연쇄가 일어나지 않을 때까지 반복
        while (true) {
            boolean isFinish = true;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    // 뿌요를 만날 경우
                    if (map[i][j] != '.') {
                        ArrayList<int[]> puyo = bfs(i, j);

                        // 같은 색의 뿌요가 4개 이상일 경우
                        if (puyo.size() >= 4) {
                            for (int[] point : puyo) {
                                map[point[0]][point[1]] = '.'; // 뿌요가 터지기 때문에 빈 공간으로 만들어줌
                            }

                            isFinish = false;
                        }
                    }
                }
            }

            if (isFinish) {
                break;
            }

            movePuyo();

            answer++; // 연쇄가 일어난 횟수 증가 (터질 수 있는 뿌요가 여러 그룹이 있는 경우 한번의 연쇄가 추가되기 때문에 여기서 증가 시킴)
        }

        System.out.println(answer);
    }

    // 상하좌우로 같은 색 뿌요가 연결되어 있는지 확인하고 같은 색 뿌요 리스트를 반환
    private static ArrayList<int[]> bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];
        ArrayList<int[]> result = new ArrayList<>(); // 같은 색 뿌요를 저장

        q.add(new int[] { x, y });
        visited[x][y] = true;
        result.add(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 벗어나지 않고, 아직 방문하지 않았으며, 이전 뿌요의 색과 같은 경우
                if (checkRange(nx, ny) && !visited[nx][ny] && map[x][y] == map[nx][ny]) {
                    q.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                    result.add(new int[] { nx, ny });
                }
            }
        }

        return result;
    }

    // 뿌요들을 아래로 떨어뜨리기 (아래 바닥이나 다른 뿌요가 나올 때까지 떨어뜨림)
    private static void movePuyo() {
        // 1. 첫 번째 방법
        while (true) {
            boolean flag = true;

            for (int j = 0; j < 6; j++) {
                for (int i = 11; i > 0; i--) {
                    if (map[i][j] == '.' && map[i - 1][j] != '.') {
                        map[i][j] = map[i - 1][j];
                        map[i - 1][j] = '.';

                        flag = false;
                    }
                }
            }

            if (flag) {
                break;
            }
        }

        // 2. 두 번째 방법
        // Queue<Character> q = new LinkedList<>();

        // for (int j = 0; j < 6; j++) {
        //     for (int i = 11; i >= 0; i--) {
        //         if (map[i][j] != '.') {
        //             q.add(map[i][j]);
        //         }
                    
        //         map[i][j] = '.';
        //     }

        //     int idx = 11;
        //     while (!q.isEmpty()) {
        //         map[idx][j] = q.poll();
        //         idx--;
        //     }
        // }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }
}