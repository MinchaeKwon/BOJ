/**
 * 14658 하늘에서 별똥별이 빗발친다
 * https://www.acmicpc.net/problem/14658
 * 
 * @author minchae
 * @date 2024. 4. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, L, K;
	static ArrayList<Pair> stars = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			stars.add(new Pair(x, y));
		}
		
		int result = 0; // 트램펄린에서 튕겨내는 별의 개수
		
		for (Pair s1 : stars) {
			for (Pair s2 : stars) {
				// 특정 별들을 트램펄린 모서리에 두고 가장 많이 튕겨내는 개수를 찾음
				result = Math.max(result, bounce(s1.x, s2.y));
			}
		}

		System.out.println(K - result);
	}
	
	private static int bounce(int x, int y) {
		int cnt = 0;
		
		for (Pair s : stars) {
			if (s.x >= x && s.x <= x + L && s.y >= y && s.y <= y + L) {
				cnt++;
			}
		}
		
		return cnt;
	}

}
