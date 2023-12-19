/**
 * 1325 - 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 * 
 * @author Minchae
 * @date 2023. 12. 19 수정
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] list;
	static int[] computer;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
		}
		
		//각 컴퓨터(정점)별로 해킹할 수 있는 컴퓨터 수를 셈 -> 그 중에 최대값을 가진 컴퓨터를 출력
		computer = new int[N + 1];
		int max = 0;

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];

			// dfs(i);
			bfs(i);
		}
		
		//최대로 해킹할 수 있는 컴퓨터 수를 구함
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, computer[i]);
		}
		
		//가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터가 여러개일수도 있기 때문에 for문을 돌면서 각 컴퓨터 번호를 출력
		for (int i = 1; i <= N; i++) {
			if (computer[i] == max) {
				bw.write(i + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int start) {
		visited[start] = true;

		for (int node : list[start]) {
			if (!visited[node]) {
				computer[node]++;
				dfs(node);
			}
		}
		
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();

		q.add(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int node : list[cur]) {
				if (!visited[node]) {
					computer[node]++;

					q.add(node);
					visited[node] = true;
				}
			}
		}
	}

}
