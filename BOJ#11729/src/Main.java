/**
 * 11729 하노이 탑 이동 순서
 * https://www.acmicpc.net/problem/11729
 * 
 * @author minchae
 * @date 2024. 7. 23.
 */

import java.io.*;

public class Main {

	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		// 옮긴 횟수 출력
		sb.append((int) Math.pow(2, N) - 1 + "\n");
		
		// 수행 과정 출력
		solve(N, 1, 2, 3);
		
		System.out.println(sb.toString());
	}
	
	// 1, 2, 3개의 장대
	private static void solve(int n, int start, int mid, int end) {
		if (n == 1) {
			sb.append(start + " " + end + "\n");
			return;
		}
		
		solve(n - 1, start, end, mid);
		
		sb.append(start + " " + end + "\n");
		
		solve(n - 1, mid, start, end);
	}

}
