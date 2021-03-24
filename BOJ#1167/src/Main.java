/**
 * 1167 트리의 지름
 * https://www.acmicpc.net/problem/1167
 * 
 * @author minchae
 * @date 2021.3.22
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
	
	static int len; // 가장 긴 지름
	static int lenNode; // 특정 노드로부터 가장 긴 지름을 가지는 노드 번호 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int V = Integer.parseInt(br.readLine());
		
		list = new LinkedList[V + 1];
		visited = new boolean[V + 1];
		
		for (int i = 1; i <= V; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			// -1 나올 때까지 입력 받음
			while (true) {
				int b = Integer.parseInt(st.nextToken());
				
				if (b == -1) break;
				
				int dist = Integer.parseInt(st.nextToken());
				
				list[a].add(new Node(b, dist));
			}
			
		}
		
		dfs(1, 0); // 시작점에서 가장 먼 노드를 찾음 
		
		Arrays.fill(visited, false);
		dfs(lenNode, 0); // 가장 먼 노드에서 제일 멀리 있는 노드를 찾음 
		
		System.out.println(len);
		

	}

	public static void dfs(int start, int d) {
		visited[start] = true;
		
		// 현재 노드의 거리가 len보다 큰 경우 len과 lenNode를 갱신해줌 
		if (d > len) {
			len = d;
			lenNode = start;
		}
		
		for (Node n : list[start]) {
			if (!visited[n.node]) {
				dfs(n.node, d + n.dist);
			}
		}
		
	}
	
	
}
