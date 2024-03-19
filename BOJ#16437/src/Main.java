/**
 * 16437 양 구출 작전
 * https://www.acmicpc.net/problem/16437
 * 
 * @author minchae
 * @date 2024. 3. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		char type;
		int cnt;
		
		public Node(char type, int cnt) {
			this.type = type;
			this.cnt = cnt;
		}
	}
	
	static int N;
	
	static ArrayList<Integer>[] list;
	static Node[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		info = new Node[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		info[1] = new Node('S', 0); // 구명보트
		
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			char type = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			
			list[next].add(i);
			info[i] = new Node(type, cnt);
		}
		
		long result = dfs(1);
		System.out.println(result);
	}
	
	private static long dfs(int idx) {
		long answer = 0;
		
		for (int next : list[idx]) {
			answer += dfs(next);
		}
		
		if (info[idx].type == 'S') {
			return answer + info[idx].cnt;
		} else {
			return answer - info[idx].cnt > 0 ? answer - info[idx].cnt : 0;
		}
	}

}
