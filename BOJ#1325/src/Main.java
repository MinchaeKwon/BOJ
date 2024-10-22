/**
 * 1325 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 * 
 * @author Minchae
 * @date 2024. 10. 22.
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer>[] list;
	static int[] computer;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		computer = new int[N + 1]; // 각 컴퓨터(정점)별로 해킹할 수 있는 컴퓨터 수
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
		}
		
		int max = 0;

		for (int i = 1; i <= N; i++) {
			if (list[i].isEmpty()) {
				continue;
			}
			
			Queue<Integer> q = new LinkedList<>();
			visited = new boolean[N + 1];

			q.add(i);
			visited[i] = true;

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
		
		// 최대로 해킹할 수 있는 컴퓨터 수를 구함
		for (int i = 1; i <= N; i++) {
			if (computer[i] > max) {
				max = computer[i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터가 여러개일수도 있기 때문에 for문을 돌면서 각 컴퓨터 번호를 출력
		for (int i = 1; i <= N; i++) {
			if (computer[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb.toString());
	}

}
