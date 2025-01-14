/**
 * 18405 경쟁적 전염
 * https://www.acmicpc.net/problem/18405
 *
 * @author minchae
 * @date 2025. 1. 9.
 **/

import java.io.*;
import java.util.*;

public class Main {

    static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int num;

        public Virus(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Virus o) {
            return Integer.compare(this.num, o.num);
        }
    }

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, K;
    static int S, X, Y;
    static int[][] map;

    static Queue<Virus> q;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        q = new LinkedList<>();
        visited = new boolean[N][N];

        ArrayList<Virus> virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0) {
                    virus.add(new Virus(i, j, map[i][j]));

                }
            }
        }

        Collections.sort(virus);

        for (Virus v : virus) {
            q.add(new Virus(v.x, v.y, v.num));
            visited[v.x][v.y] = true;
        }

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {
            spread();
        }

        System.out.println(map[X][Y]);
    }

    // 바이러스 증식
    private static void spread() {
        int size = q.size();

        for (int i = 0; i < size; i++) {
            Virus cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] > 0) {
                    continue;
                }

                q.add(new Virus(nx, ny, cur.num));
                visited[nx][ny] = true;

                map[nx][ny] = cur.num;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
