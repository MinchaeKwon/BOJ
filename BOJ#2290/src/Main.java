/**
 * 2290 LCD Test
 * https://www.acmicpc.net/problem/2290
 * 
 * @author minchae
 * @date 2024. 3. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int s;
	static char[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		char[] num = st.nextToken().toCharArray();
		
		int w = (s + 2) * num.length;
		int h = 2 * s + 3;
		
		result = new char[h][w + 1];
		
		for (int i = 0; i < num.length; i++) {
			LCD(num[i], i);
		}

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < h; i++) {
			for (int j = 1; j <= w; j++) {
				if (result[i][j] == '-' || result[i][j] == '|') {
					sb.append(result[i][j]);	
				} else {
					sb.append(" ");	
				}
				
				if (j % (s + 2) == 0) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void LCD(char n, int idx) {
		switch (n) {
		case '0':
			hypen(idx, 0);
			vertical(idx, 1);
			vertical(idx, 2);
			vertical(idx, 3);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		case '1':
			vertical(idx, 3);
			vertical(idx, 4);
			break;
		case '2':
			hypen(idx, 0);
			vertical(idx, 3);
			hypen(idx, 1);
			vertical(idx, 2);
			hypen(idx, 2);
			break;
		case '3':
			hypen(idx, 0);
			vertical(idx, 3);
			hypen(idx, 1);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		case '4':
			vertical(idx, 1);
			vertical(idx, 3);
			hypen(idx, 1);
			vertical(idx, 4);
			break;
		case '5':
			hypen(idx, 0);
			vertical(idx, 1);
			hypen(idx, 1);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		case '6':
			hypen(idx, 0);
			vertical(idx, 1);
			hypen(idx, 1);
			vertical(idx, 2);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		case '7':
			hypen(idx, 0);
			vertical(idx, 3);
			vertical(idx, 4);
			break;
		case '8':
			hypen(idx, 0);
			vertical(idx, 1);
			vertical(idx, 3);
			hypen(idx, 1);
			vertical(idx, 2);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		case '9':
			hypen(idx, 0);
			vertical(idx, 1);
			vertical(idx, 3);
			hypen(idx, 1);
			vertical(idx, 4);
			hypen(idx, 2);
			break;
		}
	}
	
	private static void hypen(int idx, int cmd) {
		int row = cmd * s + cmd;
		int col = idx * (s + 2) + 2;

		for (int i = 0; i < s; i++) {
			result[row][col + i] = '-';	
		}
	}

	private static void vertical(int idx, int cmd) {
		if (cmd == 1 || cmd == 2) { // 1, 2일 때
			int row = (cmd - 1) * s + cmd;
			int col = idx * (s + 2) + 1;
			
			for (int i = 0; i < s; i++) {
				result[row + i][col] = '|';
			}
			
		} else if (cmd == 3 || cmd == 4) { // 3, 4일 때
			int row = (cmd - 3) * s + (cmd - 2);
			int col = idx * (s + 2) + s + 2;
			
			for (int i = 0; i < s; i++) {
				result[row + i][col] = '|';
			}
		}
	}

}
