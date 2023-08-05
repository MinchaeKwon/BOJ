/**
 * 5022 연결
 * https://www.acmicpc.net/problem/5022
 * 
 * @author minchae
 * @date 2023. 8. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Wire {
    int x;
    int y;
    int dist = 0;

    public Wire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Wire (int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 행

        Wire[] point = new Wire[4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            // 행, 열이 반대로 입력돼서 x, y 순서 바꿔서 입력 받음
            int y = Integer.parseInt(st.nextToken()); // 열 번호
            int x = Integer.parseInt(st.nextToken()); // 행 번호

            point[i] = new Wire(x, y);
        }

        int result1 = bfs(point[0], point[1], point[2], point[3]); // A부터 시작
        int result2 = bfs(point[2], point[3], point[0], point[1]); // B부터 시작

        // 둘 중에서 최솟값 출력
        System.out.println(result1 == Integer.MAX_VALUE && result2 == Integer.MAX_VALUE ? "IMPOSSIBLE" : Math.min(result1, result2));
    }

    // 두 점을 연결하기 위해 bfs 사용
    // A1, A2 연결한 후에 해당 경로 방문 처리 -> 전선이 서로 겹치면 안되기 때문
    private static int bfs(Wire A1, Wire A2, Wire B1, Wire B2) {

        // 1. A1, A2 연결
        Queue<Wire> q = new LinkedList<>();
        boolean[][] visited = new boolean[M + 1][N + 1]; // 전선이 격자판의 수직, 수평선 위에 있어야 해서 M, N도 포함됨 -> 범위 +1 해줌

        q.add(A1);
        visited[A1.x][A1.y] = true;

        // B1, B2가 있는 곳은 A를 연결하는 전선이 지나가면 안되기 때문에 방문처리 해줌
        visited[B1.x][B1.y] = true;
        visited[B2.x][B2.y] = true;

        int aDist = 0;
        int bDist = 0;

        Wire[][] route = new Wire[M + 1][N + 1]; // 경로 저장

        while (!q.isEmpty()) {
            Wire cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;

            // A2에 도착한 경우
            if (x == A2.x && y == A2.y) {
                aDist = dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나지 않고 아직 방문하지 않은 경우
                if (nx >= 0 && nx <= M && ny >= 0 && ny <= N && !visited[nx][ny]) {
                    q.add(new Wire(nx, ny, dist + 1));
                    visited[nx][ny] = true;

                    route[nx][ny] = cur; // 역추적 하기 위해 이전 정보를 저장
                }
            }
        }

        // 2. B1, B2연결
        visited = new boolean[M + 1][N + 1];

        // 연결하기 전에 A2부터 A1까지의 경로를 역추적 해서 해당 경로 지나가지 않도록 함

        // 역추적 방법 1
        // Wire now = A2;
        // visited[now.x][now.y] = true;

		// while (true) {
        //     visited[now.x][now.y] = true;

		// 	if (now.x == A1.x && now.y == A1.y) {
        //         break;
        //     }

		// 	now = route[now.x][now.y]; // 역추적
		// }

        // 역추적 방법 2
        int sx = A2.x;
		int sy = A2.y;

		while (sx != A1.x || sy != A1.y) {
            visited[sx][sy] = true;

			Wire next = route[sx][sy]; // 역추적

			sx = next.x;
			sy = next.y;
		}

        visited[A1.x][A1.y] = true;
        visited[A2.x][A2.y] = true;

        q = new LinkedList<>();

        q.add(B1);
        visited[B1.x][B1.y] = true;

        while (!q.isEmpty()) {
            Wire cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;

            // B2에 도착한 경우
            if (x == B2.x && y == B2.y) {
                bDist = dist;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나지 않고 아직 방문하지 않은 경우
                if (nx >= 0 && nx <= M && ny >= 0 && ny <= N && !visited[nx][ny]) {
                    q.add(new Wire(nx, ny, dist + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return aDist == 0 || bDist == 0 ? Integer.MAX_VALUE : aDist + bDist; // 연결할 수 없는 경우 0 반환
    }

}