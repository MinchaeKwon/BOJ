/**
 * 15662 톱니바퀴 (2)
 * https://www.acmicpc.net/problem/15662
 * 
 * @author minchae
 * @date 2024. 4. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static int[][] gear;
	static int[] dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		gear = new int[T][8];
		
		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()); // 1 시계방향, -1 반시계 방향
			
			dir = new int[T];
			
			compare(n, d);
			rotate();
		}
		
		int answer = 0;

		for (int i = 0; i < T; i++) {
			if (gear[i][0] == 1) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	// 방향이 바뀌는 톱니바퀴 확인
	private static void compare(int n, int d) {
		dir[n] = d;
		
		int prev = n - 1;
		int next = n + 1;
		
		// 범위를 벗어나지 않고, 바뀌는 방향이 없고, 극이 다른 경우
		if (prev >= 0 && dir[prev] == 0 && gear[n][6] != gear[prev][2]) {
			compare(prev, d * -1);
		}
		
		if (next < T && dir[next] == 0 && gear[n][2] != gear[next][6]) {
			compare(next, d * -1);
		}
	}
	
	// 톱니바퀴 회전
	private static void rotate() {
		for (int i = 0; i < T; i++) {
			// 방향이 그대로인 경우 다음 톱니바퀴로 넘어감
			if (dir[i] == 0) {
				continue;
			}
			
			int[] tmp = new int[8];
			
			for (int j = 0; j < 8; j++) {
				int idx = j + dir[i];
				
				if (idx == -1) {
					idx = 7;
				} else if (idx == 8) {
					idx = 0;
				}
				
				tmp[idx] = gear[i][j];
			}
			
			gear[i] = tmp;
		}
	}

}
