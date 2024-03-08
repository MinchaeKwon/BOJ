/**
 * 13023 ABCDE
 * https://www.acmicpc.net/problem/13023
 * 
 * @author minchae
 * @date 2024. 3. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(1, i);
		}
		
		System.out.println(0);
	}
	
	private static void dfs(int depth, int idx) {
		if (depth == 5) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[idx] = true;
		
		for (int n : list[idx]) {
			if (!visited[n]) {
				dfs(depth + 1, n);
			}
		}
		
		visited[idx] = false;
	}

}
