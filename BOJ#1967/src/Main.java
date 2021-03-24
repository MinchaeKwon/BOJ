/**
 * 1967 트리의 지름
 * https://www.acmicpc.net/problem/1967
 * 
 * @author minchae
 * @date 2021.3.23
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		list = new LinkedList[n + 1];
		
		for (int i = 1; i <= n ; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
		}
		

	}

}
