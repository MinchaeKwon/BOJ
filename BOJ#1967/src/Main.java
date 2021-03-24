/**
 * 1967 트리의 지름
 * https://www.acmicpc.net/problem/1967
 * 
 * @author minchae
 * @date 2021.3.23
 * 
 * 트리의 지름 - 특정 정점에서 가장 멀리 있는 정점과 이 정점에서 가장 멀리 있는 정점 사이의 거리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
	int node; // 특정 노드와 연결된 다른 노드 
	int dist; // 노드 사이의 거리 
	
	public Node(int node, int dist) {
		this.node = node;
		this.dist = dist;
	}
}

public class Main {
	
	static LinkedList<Node>[] list;
	static boolean[] visited;
	
	static int max; // 가장 긴 지름
	static int maxNode; // 특정 노드로부터 가장 긴 지름을 가지는 노드 번호 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		list = new LinkedList[n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 1; i <= n ; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// 트리는 무방향 그래프이므로 둘 다 연결 해줘야함 
			list[a].add(new Node(b, dist));
			list[b].add(new Node(a, dist));
		}
		
		// n이 1인 경우에 dfs를 돌리면 런타임 에러 (NullPointer) 발생함 
		if (n > 1) {
			dfs(1, 0); // 시작점에서 가장 먼 노드를 찾음 
			
			Arrays.fill(visited, false);
			dfs(maxNode, 0); // 가장 먼 노드에서 제일 멀리 있는 노드를 찾음 	
		}
		
		System.out.println(max);

	}
	
	public static void dfs(int start, int d) {
		visited[start] = true;
		
		// 현재 노드의 거리가 max보다 큰 경우 max와 maxNode를 갱신해줌 
		if (d > max) {
			max = d;
			maxNode = start;
		}
		
		for (Node n : list[start]) {
			if (!visited[n.node]) {
				dfs(n.node, d + n.dist);
			}
		}
	}

}
