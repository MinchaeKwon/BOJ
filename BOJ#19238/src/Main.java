/**
 * 스타트 택시
 * https://www.acmicpc.net/problem/19238
 * 
 * @author minchae
 * @date 2023. 5. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x;
    int y;
    int dist;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        if (this.dist == o.dist) {
            if (this.x == o.x) {
                return this.y - o.y; // 열 번호를 기준으로 오름차순 정렬
            }
    
            return this.x - o.x; // 행 번호를 기준으로 오름차순 정렬
        }
    
        return this.dist - o.dist; // 거리를 기준으로 오름차순 정렬 -> 최단거리 가장 짧은 승객 찾기 위함
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M, fuel;
    static int[][] map;

    static int sx, sy; // 운전을 시작하는 칸의 행, 열 번호
    static Node[] passengers; // 승객 정보 저장
    static Node[] destination; // 승객의 목적지 정보 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        passengers = new Node[M + 1];
        destination = new Node[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈칸, 1은 벽
                map[i][j] = map[i][j] == 1 ? -1 : map[i][j]; // 승객 정보 저장하기 위해 1(벽)인 경우 -1로 변경
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            // 승객 정보
            int px = Integer.parseInt(st.nextToken()) - 1;
            int py = Integer.parseInt(st.nextToken()) - 1;
            map[px][py] = i; // 승객들의 출발지가 겹치지 않기 때문에 맵에 저장
            passengers[i] = new Node(px, py);

            // 목적지 정보
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            destination[i] = new Node(ex, ey);
        }

        // 승객의 수만큼 반복
        for (int i = 0; i < M; i++) {
            int pos = findPassenger();

            if (pos == -1) {
                System.out.println(-1);
                return;
            }

            sx = passengers[pos].x;
            sy = passengers[pos].y;

            if (goToArrival(destination[pos])) {
                // 목적지까지 도착하면 시작위치 변경
                sx = destination[pos].x;
                sy = destination[pos].y;
            } else {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(fuel);
    }

    // 태울 승객 번호 찾기
    private static int findPassenger() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 승객을 발견한 경우
            if (map[node.x][node.y] > 0) {
                fuel -= node.dist;

                int passengerNum = map[node.x][node.y];
                map[node.x][node.y] = 0; // 빈칸으로 변경

                return fuel >= 0 ? passengerNum : -1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
        
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny] != -1 && !visited[nx][ny]) {
                        pq.add(new Node(nx, ny, node.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return -1;
    }

    // 목적지까지 경로 찾기
    private static boolean goToArrival(Node arrival) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 목적지에 도착한 경우
            if (node.x == arrival.x && node.y == arrival.y) {
                fuel -= node.dist;

                if (fuel < 0) {
                    return false;
                }

                fuel += node.dist * 2; // 연료 충전
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny] != -1 && !visited[nx][ny]) {
                        pq.add(new Node(nx, ny, node.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        // 목적지까지 갈 수 없는 경우
        return false;
    }

}