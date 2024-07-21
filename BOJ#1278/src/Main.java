/**
 * 1278 연극
 * https://www.acmicpc.net/problem/1278
 * 
 * @author minchae
 * @date 2024. 7. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int N;
	
	static int K = 0; // 최대 장면 수
	static int[] visited = new int[1000000];
	static ArrayList<Integer> cur, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cur = new ArrayList<>();
		answer = new ArrayList<>();
		
		solve(0, 0);
		
		System.out.println(K - 1);
		
		for (int n : answer) {
			System.out.println(n + 1);
		}
	}
	
	// 무대에 서는 배우 순서 정하기 (n 무대 장면 수, m 무대에 있는 배우의 수)
	private static void solve(int n, int m) {
		if (n != 0 && m == 0 && n > K) {
			K = n;
			
			answer.clear();
			answer.addAll(cur);
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (((1 << i) & m) > 0) {
				int k = (~(1 << i)) & m;
				
				if ((n != 0 && k == 0) || visited[k] == 0) {
					visited[k] = 1; // 방문처리
					
					cur.add(i);
					solve(n + 1, k);
					cur.remove(cur.size() - 1);
				}
			}
			
			if (((1 << i) & m) == 0) {
				int k = (1 << i) | m;
				
				if (visited[k] == 0) {
					visited[k] = 1;
					
					cur.add(i);
					solve(n + 1, k);
					cur.remove(cur.size() - 1);
				}
			}
		}
	}

}
