/**
 * 7682 틱택토
 * https://www.acmicpc.net/problem/7682
 * 
 * @author minchae
 * @date 2024. 2. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String input = br.readLine();
			
			if (input.equals("end")) {
				break;
			}
			
			map = new char[3][3];
			
			int xCnt = 0;
			int oCnt = 0;
			
			int idx = 0;
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = input.charAt(idx++);
					
					if (map[i][j] == 'X') {
						xCnt++;
					} else if (map[i][j] == 'O') {
						oCnt++;
					}
				}
			}
			
			// X와 O의 개수가 같거나 X의 개수가 1개 더 많아야 게임이 끝남
			// 판이 다 찬 경우도 X의 개수가 1개 더 많음 (X가 말을 먼저 놓았기 때문)
			
			if (xCnt == oCnt + 1) {
				if (xCnt + oCnt == 9 && !checkBingo('O')) {
					// 판이 다 찼고, 'O'가 이기지 않는 경우
					sb.append("valid\n");
				} else if (checkBingo('X') && !checkBingo('O')) {
					// 한 명이 빙고를 완성하면 게임이 끝나기 때문에 둘 다 이길 수 없음
					// X가 이기는 경우
					sb.append("valid\n");
				} else {
					sb.append("invalid\n");
				}
			} else if (xCnt == oCnt) {
				if (checkBingo('O') && !checkBingo('X')) {
					sb.append("valid\n");
				} else {
					sb.append("invalid\n");
				}
			} else {
				sb.append("invalid\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean checkBingo(char c) {
		// 가로 빙고 확인
		for (int i = 0; i < 3; i++) {
			if (map[i][0] == c && map[i][1] == c && map[i][2] == c) {
				return true;
			}
		}
		
		// 세로 빙고 확인
		for (int i = 0; i < 3; i++) {
			if (map[0][i] == c && map[1][i] == c && map[2][i] == c) {
				return true;
			}
		}
		
		// 대각선 빙고 확인
		if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
			return true;
		}
		
		if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {
			return true;
		}
		
		return false;
	}

}
