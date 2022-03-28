/**
 * 15657 N과 M (8)
 * https://www.acmicpc.net/problem/15657
 * 
 * @author minchae
 * @date 2022. 3. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static int[] result;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);

		dfs(0, 0);
		System.out.println(sb);
	}
	
	private static void dfs(int start, int depth) {
		if (depth == M) {
			for (int num : result) {
				sb.append(num + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			dfs(i, depth + 1);
		}
	}

}