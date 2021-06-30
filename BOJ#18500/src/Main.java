/**
 * 18500 미네랄2
 * https://www.acmicpc.net/problem/18500
 * 
 * @author minchae
 * @date 2021. 7. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Mineral {
	int x;
	int y;
	
	public Mineral(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0}; // 상좌
	static int[] dy = {0, -1, 0, 1}; // 하우
	
	static int R, C;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			// 막대기 높이 -> 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미하므로 R에서 빼줌
			int height = R - Integer.parseInt(st.nextToken());
			
			throwStick(height, i);
			dropCluster();
		}
		
		for (int i = 0; i < R; i++) {
			System.out.println(map[i]);
		}

	}
	
	// 막대 던지기
	public static void throwStick(int row, int dir) {
		// 왼쪽에서 막대기를 던질 경우
		if (dir % 2 == 0) {
			for (int i = 0; i < C; i++) {
				// 미네랄이 있는 경우 파괴
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					break; // 막대가 날아가다가 미네랄을 만나면, 그 칸에 있는 미네랄은 모두 파괴되고 막대는 그 자리에서 이동을 멈춤
				}
			}
		}
		else { // 오른쪽에서 막대기를 던질 경우
			for (int i = C - 1; i >= 0; i--) {
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					break;
				}
			}
		}
	}
	
	// 클러스터 떨어뜨리기
	public static void dropCluster() {
		boolean[][] visited = new boolean[R][C];
		Queue<Mineral> q = new LinkedList<>();
		
		// 땅에 있는 미네랄 클러스터 체크
		for (int i = 0; i < C; i++) {
			if (map[R - 1][i] == 'x' && !visited[R - 1][i]) {
				visited[R - 1][i] = true;
				q.add(new Mineral(R - 1, i));
				
				while (!q.isEmpty()) {
					Mineral mineral = q.poll();
					
					// 상하좌우를 탐색하면서 땅에 있는 클러스터를 체크
					for (int j = 0; j < 4; j++) {
						int nx = mineral.x + dx[j];
						int ny = mineral.y + dy[j];
						
						if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
							if (map[nx][ny] == 'x' && !visited[nx][ny]) {
								visited[nx][ny] = true;
								q.add(new Mineral(nx, ny));	
							}
						}
					}
				}
			}
		}
		
		// 공중에 떠 있는 미네랄 클러스터를 저장하기 위한 리스트
		ArrayList<Mineral> cluster = new ArrayList<>();
		
		// 공중에 떠 있는 미네랄 클러스터 체크
		// 위의 for문에서 탐색되지 못한 클러스터는 공중에 있는 것임
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] == 'x') {
					cluster.add(new Mineral(i, j));
					map[i][j] = '.';
				}
			}
		}
		
		// 공중에 떠 있는 미네랄 클러스터가 없을 경우 종료
		if (cluster.isEmpty()) {
			return;
		}
		
		boolean flag = true;
		
		// 미네랄 클러스터를 떨어뜨릴 수 있는지 확인(아래 행에 클러스터가 있는지 없는지 확인)
		while (flag) {
			for (Mineral m : cluster) {
				int nx = m.x + 1; // 밑으로 내릴 수 있는지 확인하기 위해 +1을 하는 것
				int ny = m.y;
				
				// 떨어지는 동안 클러스터의 모양은 변하지 않기 때문에 클러스터 중 하나라도 밑으로 내릴 수 없는 경우 flag = false로 바꿔줌
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == 'x') {
					flag = false;
					break;
				}
				
			}
			
			// 모든 클러스터가 밑으로 내려갈 수 있는 경우에만 위치를 바꿔줌
			if (flag) {
				for (Mineral m : cluster) {
					m.x += 1;
				}
			}
			
		}
		
		// 미네랄 클러스터 떨어뜨리기
		for (Mineral m : cluster) {
			map[m.x][m.y] = 'x';
		}
		
	}

}
