/**
 * 2630 색종이 자르기
 * https://www.acmicpc.net/problem/2630
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[][] map;
	
	static int white, blue;
	
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
		
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void cut(int x, int y, int size) {
		if (check(x, y, size)) {
			if (map[x][y] == 0) {
				white++;
			} else {
				blue++;
			}
			
			return;
		}
		
		int half = size / 2; // 반으로 자름
		
		for (int i = x; i < x + size; i += half) {
			for (int j = y; j < y + size; j += half) {
				cut(i, j, half);
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
