/**
 * 14891 톱니바퀴
 * https://www.acmicpc.net/problem/14891
 * 
 * @author minchae
 * @date 2021. 4. 15
 * 
 * N극은 0, S극은 1
 * 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] gear;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			rotation(num, dir);
			
			
		}
		
	}
	
	public static void rotation(int n, int dir) {
		// 시계 방향인 경우
		if (dir == 1) {
			int tmp = gear[n][7];
			
			for (int i = 7; i >= 0; i--) {
				gear[n][i] = gear[n][i - 1];
			}
			
			gear[n][0] = tmp;
		}
		else { // 반시계 방향인 경우
			int tmp = gear[n][0];
			
			for (int i = 0; i < 7; i++) {
				gear[n][i] = gear[n][i + 1];
			}
			
			gear[n][7] = tmp;
		}
	}

}
