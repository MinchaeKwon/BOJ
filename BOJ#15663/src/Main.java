/**
 * 15653 N과 M (9)
 * https://www.acmicpc.net/problem/15663
 * 
 * @author minchae
 * @date 2022. 3. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static boolean[] visited;
	static int[] selected;
	static LinkedHashSet<String> result; // 중복을 제외하기 위해 사용하는 것 (입력한 순서대로 저장됨)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		selected = new int[M];
		visited = new boolean[N];
		
		result = new LinkedHashSet<>();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0);
		
		result.forEach(System.out::println);
	}
	
	private static void dfs(int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : selected) {
				sb.append(num + " ");
			}
			
			result.add(sb.toString());
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = arr[i];
				
				dfs(depth + 1);
				
				visited[i] = false;
			}
		}
	}

}
