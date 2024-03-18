/**
 * 6987 월드컵
 * https://www.acmicpc.net/problem/6987
 * 
 * @author minchae
 * @date 2024. 3. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] team1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] team2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	
	static int[] win;
	static int[] lose;
	static int[] draw;
	
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 0; t < 4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			win = new int[6];
			draw = new int[6];
			lose = new int[6];
			
			int sum = 0;
			
			for (int i = 0; i < 6; i++) {
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
				
				sum += win[i] + draw[i] + lose[i];
			}
			
			flag = false;
			
			if (sum == 30) {
				match(0);
			}
			
			System.out.print((flag ? 1 : 0) + " ");
		}

	}
	
	private static void match(int depth) {
		if (flag) {
			return;
		}
		
		if (depth == 15) {
			flag = true;
			return;
		}
		
		int t1 = team1[depth];
		int t2 = team2[depth];
		
		if (win[t1] > 0 && lose[t2] > 0) {
			win[t1]--;
			lose[t2]--;
			
			match(depth + 1);
			
			win[t1]++;
			lose[t2]++;
		}
		
		if (draw[t1] > 0 && draw[t2] > 0) {
			draw[t1]--;
			draw[t2]--;
			
			match(depth + 1);
			
			draw[t1]++;
			draw[t2]++;
		}
		
		if (lose[t1] > 0 && win[t2] > 0) {
			lose[t1]--;
			win[t2]--;
			
			match(depth + 1);
			
			lose[t1]++;
			win[t2]++;
		}
	}

}
