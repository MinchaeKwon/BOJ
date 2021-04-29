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
	static int[] rotationDir; // 회전하는 방향을 저장할 배열

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
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			rotationDir = new int[4];
			solve(num, dir);
			rotation();
		}
		
		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1) {
				result += Math.pow(2, i);
			}
		}
		System.out.println(result);
		
	}
	
	public static void solve(int num, int dir) {
		// 톱니바퀴의 회전방향을 저장
		rotationDir[num] = dir;
		
		int prev = num - 1;
		int next = num + 1;
		
		// 톱니바퀴의 왼쪽, 오른쪽 바퀴의 극을 확인한 후 양 극이 달라서 회전할 수 있다면 재귀 호출을 함
		if (prev >= 0 && rotationDir[prev] == 0) {
			if (gear[prev][2] != gear[num][6]) {
				solve(prev, dir * -1);
			}
		}
		
		if (next <= 3 && rotationDir[next] == 0) {
			
			if (gear[next][6] != gear[num][2]) {
				solve(next, dir * -1);
			}
		}
		
	}
	
	// rotationDir에 저장된 방향대로 톱니바퀴를 회전시킴
	public static void rotation() {
		for (int i = 0; i < 4; i++) {
			if (rotationDir[i] != 0) {
				int[] tmp = new int[8];
				int idx;
				
				for (int j = 0; j < 8; j++) {
					idx = j + rotationDir[i];
					
					if (idx == -1) {
						idx = 7;
					}
					else if (idx == 8) {
						idx = 0;
					}
					
					tmp[idx] = gear[i][j];
				}
				
				gear[i] = tmp;
			}
		}
	}
	
//	public static void rotation(int n, int dir) {
//		// 시계 방향인 경우
//		if (dir == 1) {
//			int tmp = gear[n][7];
//			
//			for (int i = 7; i >= 0; i--) {
//				gear[n][i] = gear[n][i - 1];
//			}
//			
//			gear[n][0] = tmp;
//		}
//		else { // 반시계 방향인 경우
//			int tmp = gear[n][0];
//			
//			for (int i = 0; i < 7; i++) {
//				gear[n][i] = gear[n][i + 1];
//			}
//			
//			gear[n][7] = tmp;
//		}
//	}

}
