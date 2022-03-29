/**
 * 15654 N과 M (10)
 * https://www.acmicpc.net/problem/15664
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
	
	static int[] selected;
	static LinkedHashSet<String> result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		selected = new int[M];
		
		result = new LinkedHashSet<>();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0, 0);
		
		result.forEach(System.out::println);
	}
	
	private static void dfs(int start, int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : selected) {
				sb.append(num + " ");
			}
			
			result.add(sb.toString());
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected[depth] = arr[i];
			dfs(i + 1, depth + 1);
		}
	}

}
