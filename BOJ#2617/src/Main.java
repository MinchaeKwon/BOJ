/**
 * 2617 구슬 찾기
 * https://www.acmicpc.net/problem/2617
 * 
 * @author minchae
 * @date 2023. 12. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 987654321;
	
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	static int[][] arr; // [i][0]에는 해당 구슬보다 가벼운 구슬의 개수, [i][1]에는 무거운 구슬의 개수를 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		arr = new int[N + 1][2];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// a가 b보다 무거운 구슬
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
		}
		
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			
			dfs(i, i);
		}
		
		int answer = 0; // 무게가 중간인 구슬이 될 수 없는 구슬의 개수
		
		int half = (N + 1) / 2;
		
		for (int i = 1; i <= N; i++) {
			// 가볍거나 무거운 구슬의 개수가 (N + 1) / 2개를 넘어갈 경우 무게가 중간인 구슬이 될 수 없음
			if (arr[i][0] >= half || arr[i][1] >= half) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int start, int cur) {
		visited[cur] = true;
		
		for (int next : list[cur]) {
			if (!visited[next]) {
				arr[start][0]++; // start번 구슬보다 가벼운 구슬이 있는 것이므로 ++해줌
				arr[next][1]++; // next번 구슬보다 무거운 구슬인 cur번 구슬이 있으므로 ++해줌
				
				dfs(start, next);
			}
		}
	}

}
