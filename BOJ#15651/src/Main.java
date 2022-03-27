/**
 * 15651 N과 M (3)
 * https://www.acmicpc.net/problem/15651
 * 
 * @author minchae
 * @date 2022. 3. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];

		dfs(0);
		System.out.println(sb);
	}
	
	private static void dfs(int depth) {
		// depth와 M이 같아지는 경우 arr에 담았던 수를 출력
		if (depth == M) {
			for (int num : arr) {
				sb.append(num + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[depth] = i + 1;
			dfs(depth + 1);
		}
	}

}
