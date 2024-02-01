/**
 * 19235 모노미노도미노
 * https://www.acmicpc.net/problem/19235
 * 
 * @author minchae
 * @date 2024. 2. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static int GREEN = 0;
	final static int BLUE = 1;
	
	static int N;
	
	static int[][][] map = new int[2][6][4]; // green 0, blue 1
	
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			 
			moveBlock(t, GREEN, 0, y, i); // 초록색 보드로 블록 옮김
			
			// 원래 파란색 보드는 4 * 6이지만 6 * 4에 맞추기 위해 값을 계산해서 넣어줌
			if (t == 1) {
				moveBlock(1, BLUE, 0, 4 - x - 1, i);
			} else if (t == 2) {
				moveBlock(3, BLUE, 0, 4 - x - 1, i);
			} else {
				moveBlock(2, BLUE, 0, 4 - (x + 1) - 1, i);
			}
			
			checkBlock(GREEN); // 초록색 보드 확인
			checkBlock(BLUE); // 파란색 보드 확인
		}
		
		System.out.println(score);
		System.out.println(getBlockCnt());
	}
	
	// 특정 블록을 다른 블록이나 경계를 만나기 전까지 이동 시킴
	// id는 각 블록의 타입을 구분하기 위해 사용하는 것
	private static void moveBlock(int t, int c, int x, int y, int id) {
		int idx = 0;
		
		if (t == 1 || t == 3) { // 1 X 1
			for (int i = x; i < 6; i++) {
				if (map[c][i][y] > 0) {
					break;
				}
				
				idx = i;
			}
			
			map[c][idx][y] = id;
			
			if (t == 3) { // 2 X 1
				// map[c][idx - 1][y]를 확인하지 않아도 되는 이유는 위의 for문에서 작은 값부터 블록이 있는지 확인했기 때문
				map[c][idx - 1][y] = id;
			}
		} else { // 1 X 2
			for (int i = x; i < 6; i++) {
				if (map[c][i][y] > 0 || map[c][i][y + 1] > 0) {
					break;
				}
				
				idx = i;
			}
			
			map[c][idx][y] = id;
			map[c][idx][y + 1] = id;
		}
	}
	
	// 특정 줄에 블록이 가득 차있는지 확인
	private static void checkBlock(int c) {
		// 2 ~ 5줄 확인
		for (int i = 2; i <= 5; i++) {
			int cnt = 0;
			
			for (int j = 0; j < 4; j++) {
				if (map[c][i][j] > 0) {
					cnt++;
				}
			}
			
			if (cnt == 4) {
				removeBlock(c, i);
				score++;
			}
		}
		
		// 연한 보드 확인
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[c][i][j] > 0) {
					removeBlock(c, 5);
					break;
				}
			}
		}
	}
	
	// 특정 줄에 있는 블록 삭제
	private static void removeBlock(int c, int x) {
		// 해당 줄 삭제
		for (int y = 0; y < 4; y++) {
			map[c][x][y] = 0;
		}
		
		// 현재 행의 위에 있는 블록을 내릴 수 있을 때까지 내림
		for (int i = x - 1; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				if (map[c][i][j] > 0) {
					int id = map[c][i][j];
					int type = getType(c, i, j);
					
					map[c][i][j] = 0;
					
					// 타입 확인해서 해당 블록 삭제함
					if (type == 2) {
						map[c][i][j + 1] = 0;
					} else if (type == 3) {
						map[c][i - 1][j] = 0;
					}
					
					moveBlock(type, c, i, j, id); // 위에 위치한 블록 떨어뜨림
				}
			}
		}
	}
	
	// 타입 확인
	private static int getType(int c, int x, int y) {
		if (y + 1 < 4 && map[c][x][y] == map[c][x][y + 1]) {
			return 2;
		}
		
		if (x - 1 >= 0 && map[c][x - 1][y] == map[c][x][y]) { 
			return 3;
		}
		
		return 1;
	}
	
	// 파란색 보드와 초록색 보드에서 타일이 들어있는 칸의 개수를 셈
	private static int getBlockCnt() {
		int cnt = 0;
		
		for (int c = GREEN; c <= BLUE; c++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (map[c][i][j] > 0) {
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}

}
