/**
 * 13460 구슬 탈출 2
 * https://www.acmicpc.net/problem/13460
 * 
 * @author minchae
 * @date 2021. 4. 10
 * 
 * '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 
 * 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치
 * 입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball {
	int ri;
	int rj;
	int bi;
	int bj;
	int move;
	
	public Ball(int ri, int rj, int bi, int bj, int move) {
		this.ri = ri;
		this.rj = rj;
		this.bi = bi;
		this.bj = bj;
		this.move = move;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N, M;
	static char[][] board;
	static boolean[][][][] visited; // 빨간구슬 행, 빨간구슬 열, 파란구슬 행, 파란구슬 열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		int ri = 0;
		int rj = 0;
		int bi = 0;
		int bj = 0;
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
				
				if (board[i][j] == 'R') {
					ri = i;
					rj = j;
				}
				else if (board[i][j] == 'B') {
					bi = i;
					bj = j;
				}
			}
		}
		
		Ball ball = new Ball(ri, rj, bi, bj, 0);
		bfs(ball);

	}
	
	public static void bfs(Ball ball) {
		Queue<Ball> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		q.add(ball);
		
		while (!q.isEmpty()) {
			Ball b = q.poll();
			visited[b.ri][b.rj][b.bi][b.bj] = true;
			
			if (b.move > 10) {
				System.out.println(-1);
				return;
			}
			
			// 파란 구슬이 구멍에 빠질 경우 기울이기를 멈추고 빨간 구슬을 꺼내기 위해 다음 위치로 감
			if (board[b.bi][b.bj] == 'O') {
				continue;
			}
			
			// 빨간 구슬이 구멍에 빠질 경우에는 지금까지 구슬을 뺀 횟수를 출력하고 종료
			if (board[b.ri][b.rj] == 'O') {
				System.out.println(b.move);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int blueR = b.bi;
				int blueC = b.bj;
				
				// 파란 구슬 기울이기
				while (board[blueR + dx[i]][blueC + dy[i]] != '#') {
					blueR += dx[i];
					blueC += dy[i];
					
					if (board[blueR][blueC] == 'O') { 
						break;
					}
				}
				
				int redR = b.ri;
				int redC = b.rj;
				
				// 빨간 구슬 기울이기
				while (board[redR + dx[i]][redC + dy[i]] != '#') {
					redR += dx[i];
					redC += dy[i];
					
					if (board[redR][redC] == 'O') { 
						break;
					}
				}
				
				// 빨간 구슬과 파란 구슬의 위치가 같고 빨간 구슬의 위치가 구멍이 아닌 경우 위치 조정
				// 이동 거리가 더 큰 구슬을 이전 위치로 되돌려놓음
				if (redR == blueR && redC == blueC && board[redR][redC] != 'O') {
					int red_dist = Math.abs(redR - b.ri) + Math.abs(redC - b.rj);
					int blue_dist = Math.abs(blueR - b.bi) + Math.abs(blueC - b.bj);
					
					if (red_dist > blue_dist) {
						redR -= dx[i];
						redC -= dy[i];
					}
					else {
						blueR -= dx[i];
						blueC -= dy[i];
					}
					
				}
				
				if (!visited[redR][redC][blueR][blueC]) {
					q.add(new Ball(redR, redC, blueR, blueC, b.move + 1));
				}
				
			}
			
		}
		
		// 10번 이하 움직여서 빨간 구슬을 빼낼 수 없는 경우
		System.out.println(-1);
		
	}

}
