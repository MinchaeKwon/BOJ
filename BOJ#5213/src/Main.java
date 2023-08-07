/**
 * 5213 과외맨
 * https://www.acmicpc.net/problem/5213
 * 
 * @author minchae
 * @date 2023. 8. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tile {
    int x;
    int y;
    ArrayList<Integer> path; // 특정 타일까지 가는 경로 저장

    public Tile(int x, int y, ArrayList<Integer> path) {
        this.x = x;
        this.y = y;
        this.path = path;
    }
}

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N;
    static int[][] map; // 각각의 타일에 들어가는 숫자 저장
    static int[][] tile; // 타일 번호 저장 (조각 두개가 타일 한개)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N * 2];
        tile = new int[N][N * 2];

        int num = 1;

        for (int i = 0; i < N; i++) {
            // 짝수, 홀수줄 구분해서 입력 받음
            int start = i % 2 == 0 ? 0 : 1;
            int end = i % 2 == 0 ? N * 2 : (N - 1) * 2;

            for (int j = start; j < end; j += 2) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                map[i][j] = Integer.parseInt(st.nextToken());
                map[i][j + 1] = Integer.parseInt(st.nextToken());

                tile[i][j] = num;
                tile[i][j + 1] = num;

                num++;
            }

        }

        Tile result = bfs();

        System.out.println(result.path.size()); // 가장 짧은 경로의 길이 (타일의 개수) 출력

        // 경로 상의 타일 번호 출력
        for (Integer path : result.path) {
            System.out.print(path + " ");
        }
    }

    private static Tile bfs() {
        Queue<Tile> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N * 2];

        ArrayList<Integer> path = new ArrayList<>();

        // 시작은 1번 타일
        path.add(1);

        q.add(new Tile(0, 0, path));
        q.add(new Tile(0, 1, path));

        visited[0][0] = true;
        visited[0][1] = true;

        int max = 0;
        Tile result = null;

        while (!q.isEmpty()) {
            Tile cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            path = cur.path;

            // 타일 번호가 큰 것으로 갱신 -> 마지막 줄의 마지막 타일로 이동할 수 없는 경우가 존재할 때 번호가 가장 큰 타일로 이동하면 되기 때문
            if (tile[x][y] > max) {
                max = tile[x][y];
                result = cur;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나지 않고, 방문하지 않았으며 빈칸이 아닌 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N * 2 && !visited[nx][ny] && map[nx][ny] != 0) {
                    // 한 타일에서 다른 타일로 넘어갈 수 있는 경우
                    if (map[x][y] == map[nx][ny]) {
                        ArrayList<Integer> nextPath = new ArrayList<>();
                        nextPath.addAll(path);
                        nextPath.add(tile[nx][ny]);

                        // 탐색한 타일의 칸 큐에 삽입
                        q.add(new Tile(nx, ny, nextPath));
                        visited[nx][ny] = true;

                        // 타일의 다른 칸을 큐에 삽입
                        if (tile[nx][ny] == tile[nx][ny + 1]) {
                            // 탐색한 칸이 타일의 왼쪽일 경우
                            q.add(new Tile(nx, ny + 1, nextPath));
                            visited[nx][ny + 1] = true;
                        } else if (tile[nx][ny - 1] == tile[nx][ny]) {
                            // 탐색한 칸이 타일의 오른쪽일 경우
                            q.add(new Tile(nx, ny - 1, nextPath));
                            visited[nx][ny - 1] = true;
                        }
                    }
                }
            }
        }

        return result;
    }
}