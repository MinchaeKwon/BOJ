/**
 * 17144 미세먼지 안녕!
 * https://www.acmicpc.net/problem/17144
 * 
 * @author minchae
 * @date 2021.5.11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		map = new int[R][R];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			
			for (int j = 0; j < R; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
			}
			
		}
		

	}

}
