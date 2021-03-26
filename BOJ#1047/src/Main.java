/**
 * 1074 Z
 * https://www.acmicpc.net/problem/1074
 * 
 * @author minchae
 * @date 2021. 3. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// size는 사각형 한 변의 길이
		int size = (int) Math.pow(2, N);
		
		System.out.println(solve(size, r, c));

	}
	
	public static int solve(int size, int r, int c) {
		if (size == 1) {
			return 0;
		}
		
		int half = size / 2;
		
		// r, c가 첫번째 칸에 있을 경우
		if (r < half && c < half) {
			// 첫번째 칸에 있는 경우에는 0번째부터 방문하므로 더해줄 필요 없이 바로 재귀 호출 
			return solve(half, r, c);
		}
		else if (r < half && c >= half) { // r, c가 두번째 칸에 있을 경우
			// 첫번째 칸을 먼저 방문해야하므로 첫번째 칸의 크기를 더해줌 
			return solve(half, r, c - half) + (int) Math.pow(half, 2);
		}
		else if (r >= half && c < half) { // r, c가 세번째 칸에 있을 경우
			// 첫번째, 두번째 칸을 먼저 방문해야하므로 두개의 사각형 크기를 더해줌 
			return solve(half, r - half, c) + (int) Math.pow(half, 2) * 2;
		}
		else { // r, c가 네번째 칸에 있을 경우
			 // 첫번째, 두번째, 세번째 칸을 먼저 방문해야하므로 세개의 사각형 크기를 더해줌
			return solve(half, r - half, c - half) + (int) Math.pow(half, 2) * 3;  
		}
		
	}

}
