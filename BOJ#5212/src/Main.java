/**
 * 5212 지구 온난화
 * https://www.acmicpc.net/problem/5212
 * 
 * @author minchae
 * @date 2024. 2. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int R, C;
	
	static char[][] map; // 'X'는 땅, '.'는 바다
	static boolean[][] visited; // 50년 후에 잠기는 땅을 체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int minR = R;
		int maxR = 0;
		
		int minC = C;
		int maxC = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 땅인 경우
				if (map[i][j] == 'X') {
					int cnt = 0;
					
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						// 범위를 벗어나거나(범위를 벗어나는 칸도 바다) 바다인 경우
						if (!isRange(nx, ny) || map[nx][ny] == '.') {
							cnt++;
						}
					}
					
					if (cnt >= 3) { // 인접한 세 칸 또는 네 칸이 바다인 경우
						visited[i][j] = true;
					} else { // 아닌 경우 섬을 포함하는 가장 작은 직사각형 범위를 구함
						minR = Math.min(minR, i);
						maxR = Math.max(maxR, i);
						
						minC = Math.min(minC, j);
						maxC = Math.max(maxC, j);
					}
				}
			}
		}
		
		for (int i = minR; i <= maxR; i++) {
			for (int j = minC; j <= maxC; j++) {
				if (visited[i][j]) {
					map[i][j] = '.';
				}
				
				System.out.print(map[i][j]);
			}
			
			System.out.println();
		}

	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
