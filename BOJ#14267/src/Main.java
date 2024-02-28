/**
 * 14367 회사 문화 1
 * https://www.acmicpc.net/problem/14367
 * 
 * @author minchae
 * @date 2024. 2. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	
	static ArrayList<Integer>[] list;
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n + 1];
		cnt = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {	
			int num = Integer.parseInt(st.nextToken());
			
			if (num == -1) {
				continue;
			}
			
			list[num].add(i); // num의 부하 번호 저장
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			cnt[num] += w;
		}
	
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) {
			sb.append(cnt[i] + " ");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int idx) {
		for (int num : list[idx]) {
			cnt[num] += cnt[idx]; // 부하의 칭찬 수치에 자신의 칭찬 수치 더함
			
			dfs(num); // 연쇄적으로 칭찬
		}
	}

}
