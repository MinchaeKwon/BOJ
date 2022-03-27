/**
 * 15649 N과 M (1)
 * https://www.acmicpc.net/problem/15649
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
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		visited = new boolean[N];

		dfs(0);
	}
	
	private static void dfs(int depth) {
		// depth와 M이 같아지는 경우 arr에 담았던 수를 출력
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true; // 방문 상태로 바꿔줌
				arr[depth] = i + 1;
				
				dfs(depth + 1); // 자식 노드 방문을 위해 +1을 해줌 : 재귀
				
				visited[i] = false; // 자식 노드 방문 후에는 현재 노드 방문 상태를 false로 변경 : 백트래킹
			}
		}
	}

}
