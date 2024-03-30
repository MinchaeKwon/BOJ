/**
 * 2116 주사위 쌓기
 * https://www.acmicpc.net/problem/2116
 * 
 * @author minchae
 * @date 2024. 3. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] dice;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dice = new int[N][6];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 6; i++) {
			stackDice(1, i, 0);
		}
		
		System.out.println(answer);
	}
	
	// 주사위 쌓기
	private static void stackDice(int depth, int bottom, int sum) {
		int up = getPair(bottom);
		
		int sideMax = 0;
		
		for (int i = 0; i < 6; i++) {
			if (i != up && i != bottom) {
				sideMax = Math.max(sideMax, dice[depth - 1][i]);
			}
		}
		
		sum += sideMax;
		
		if (depth == N) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i = 0; i < 6; i++) {
			// 마주보는 면의 숫자가 같아야함
			if (dice[depth - 1][up] == dice[depth][i]) {
				stackDice(depth + 1, i, sum);
				break;
			}
		}
	}
	
	// 마주보는 면 구하기
	private static int getPair(int n) {
		if (n == 0) {
			return 5;
		} else if (n == 5) {
			return 0;
		} else if (n == 1) {
			return 3;
		} else if (n == 3) {
			return 1;
		} else if (n == 2) {
			return 4;
		} else {
			return 2;
		}
	}

}
