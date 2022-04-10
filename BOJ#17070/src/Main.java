/**
 * 17070 파이프 옮기기 1
 * https://www.acmicpc.net/problem/17070
 * 
 * @author minchae
 * @date 2022. 4. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(0, 1, 0);

		System.out.println(result);
	}

	// dir 0 : 가로, 1 : 세로, 2: 대각선
	public static void move(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}
		
		switch (dir) {
		case 0: // 파이프가 가로로 놓여있을 때 오른쪽(가로)으로 이동 (빈 칸일 경우에만 이동 가능)
			if (y + 1 < N && map[x][y + 1] == 0) {
				move(x, y + 1, 0);
			}
			
			break;
		case 1: // 파이프가 세로로 놓여있을 때 아래(세로)로 이동
			if (x + 1 < N && map[x + 1][y] == 0) {
				move(x + 1, y, 1);
			}
			
			break;
		case 2: // 파이프가 대각선으로 놓여있을 때 오른쪽과 아래로 이동
			// 가로로 이동
			if (y + 1 < N && map[x][y + 1] == 0) {
				move(x, y + 1, 0);
			}
			
			// 세로로 이동
			if (x + 1 < N && map[x + 1][y] == 0) {
				move(x + 1, y, 1);
			}

			break;
		}
		
		// 오른쪽 대각선으로 이동하는 경우는 세 경우 모두 가능하기 때문에 공통으로 해줌
		if (x + 1 < N && y + 1< N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
			move(x + 1, y + 1, 2);
		}
	}
	
}
