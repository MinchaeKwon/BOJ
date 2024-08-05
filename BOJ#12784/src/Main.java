/**
 * 12784 인하니카 공화국
 * https://www.acmicpc.net/problem/12784
 * 
 * @author minchae
 * @date 2024. 8. 5.
 */

import java.io.*;
import java.util.*;

// 리프 노드와 그 부모의 가중치를 비교해 더 작은 값을 구함 -> 부모의 가중치와 자식 노드들의 가중치 합을 비교해 더 작은 값을 구하는 것
public class Main {
	
	static class Node {
		int e; // 연결된 노드
		int d; // 필요한 다이너마이트 개수
		
		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}
	}

	static int N, M;
	
	static ArrayList<Node>[] list;
	static boolean[] visited;
	
	static boolean isLeaf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[N + 1];
			visited = new boolean[N + 1];
			
			isLeaf = false;
			
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int D = Integer.parseInt(st.nextToken());
				
				list[a].add(new Node(b, D));
				list[b].add(new Node(a, D));
			}
			
			int answer = 0;
			
			// 1번 노드의 자식 노드들을 탐색
			visited[1] = true;
			
			for (Node node : list[1]) {
				answer += dfs(node.e, node.d); // 1번 노드의 자식 노드에서 반환된 최솟값을 더함
			}
		
			System.out.println(answer);
		}

	}
	
	private static int dfs(int cur, int cnt) {
		visited[cur] = true;
		
		boolean isLeaf = true;
		int childCnt = 0;
		
		for (Node node : list[cur]) {
			// 노드에 연결된 다른 노드를 방문하지 않은 경우 자식 노드가 있는 것
			if (!visited[node.e]) {
				isLeaf = false;
				childCnt += dfs(node.e, node.d); // 리프 노드가 아닌 경우 자식 노드들의 합을 구함
			}
		}
		
		// 리프 노드인 경우 자기 자신이 마지막이므로 cnt 값을 넣어줌
		if (isLeaf) {
			childCnt = cnt;
		}
		
		return Math.min(childCnt, cnt); // 둘 중에 최소값을 반환 (자식 노드들의 합, 현재 노드의 다이너마이트 수)
	}

}
