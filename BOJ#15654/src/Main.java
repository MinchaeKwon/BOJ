/**
 * 15654 N과 M (5)
 * https://www.acmicpc.net/problem/15654
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
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		result = new int[M];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0);
	}
	
	private static void dfs(int depth) {
		// depth와 M이 같아지는 경우 result에 담았던 수를 출력
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true; // 방문 상태로 바꿔줌
				result[depth] = arr[i];
				
				dfs(depth + 1); // 자식 노드 방문을 위해 +1을 해줌 : 재귀
				
				visited[i] = false; // 자식 노드 방문 후에는 현재 노드 방문 상태를 false로 변경 : 백트래킹
			}
		}
	}

}
