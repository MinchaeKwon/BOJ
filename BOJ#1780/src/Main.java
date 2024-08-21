/**
 * 1780 종이의 개수
 * https://www.acmicpc.net/problem/1780
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.util.*;
import java.io.*;

// 색종이 자르기 문제와 비슷
// 다른 점은 3등분을 해주어야 한다는 것
public class Main {

	static int N;
	static int[][] map;
	
	static int minus, zero, one;
	
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
		
		cut(0, 0, N);
		
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}
	
	private static void cut(int x, int y, int size) {
		if (check(x, y, size)) {
			if (map[x][y] == 0) {
				zero++;
			} else if (map[x][y] == 1) {
				one++;
			} else {
				minus++;
			}
			
			return;
		}
		
		int cutSize = size / 3; // 다시 자를 크기 -> 3등분
		
		for (int i = x; i < x + size; i += cutSize) {
			for (int j = y; j < y + size; j += cutSize) {
				cut(i, j, cutSize);
			}
		}
	}
	
	// 잘린 종이의 색이 같은지 확인
	private static boolean check(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != map[x][y]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
