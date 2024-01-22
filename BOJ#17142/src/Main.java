/**
 * 17142 연구소 3
 * https://www.acmicpc.net/problem/17142
 * 
 * @author minchae
 * @date 2024. 1. 18. 수정
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
    int x;
    int y;
    int time;

    public Virus(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M;
    static int[][] map; // 0은 빈 칸, 1은 벽, 2는 바이러스의 위치
    static ArrayList<Virus> virusList = new ArrayList<>();
    
    static Virus[] virus;

    static int empty = 0;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 벽, 2는 바이러스

                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j, 0));
                }

                if (map[i][j] == 0) {
                    empty++;
                }
            }
        }

        // 빈 칸이 없을 경우 바이러스가 이미 다 퍼져있는 것이기 때문에 0 출력
        if (empty == 0) {
            System.out.println(0);
        } else {
            selectActiveVirus(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer); // 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1 출력
        }

    }

    // 조합을 통해 활성 바이러스가 위치할 수 있는 곳 선택
    private static void selectActiveVirus(int start, int depth) {
        // M개의 활성 바이러스를 다 선택한 경우 bfs 실행
        if (depth == M) {
            bfs(empty);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            virus[depth] = virusList.get(i);
            selectActiveVirus(i + 1, depth + 1);
        }
    }

    // bfs를 통해 바이러스 퍼뜨림
    private static void bfs(int cnt) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N]; // 바이러스 확산 여부 저장
        
        // 활성 바이러스 위치 큐에 넣어줌
        for (Virus v : virus) {
        	q.add(new Virus(v.x, v.y, 0));
        	visited[v.x][v.y] = true;
        }

        while (!q.isEmpty()) {
            Virus cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                // 범위를 벗어나거나 이미 방문 했거나 벽인 경우 다음으로 넘어감
                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
                	continue;
                }
                
                // 비활성 바이러스가 있는 곳은 활성 바이러스로 변하기 때문에 if문에 넣지 않음
                q.add(new Virus(nx, ny, cur.time + 1));
                visited[nx][ny] = true;
                
                // 빈 칸일 경우 바이러스를 퍼뜨리기 때문에 cnt를 감소시킴
                if (map[nx][ny] == 0) {
                	cnt--;
                }
                
                // 바이러스를 다 퍼뜨린 경우(빈 칸이 다 없어진 경우) 최단 시간 갱신 후 종료
                if (cnt == 0) {
                	answer = Math.min(answer, cur.time + 1);
                	return;
                }

            }
        }

    }
    
    private static boolean isRange(int x, int y) {
    	return x >= 0 && x < N && y >= 0 && y < N;
    }

}