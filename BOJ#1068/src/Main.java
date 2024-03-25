/**
 * 1068 트리
 * https://www.acmicpc.net/problem/1068
 * 
 * @author minchae
 * @date 2024. 3. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] parent;
	
	static boolean[] visited;
	static int answer;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = -1;
		
		for (int i = 0; i < N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			
			if (parent[i] == -1) {
				root = i;
			}
		}
		
		int delete = Integer.parseInt(br.readLine());

		removeNode(delete);
		countLeaf(root);
		
		System.out.println(answer);
	}
	
	private static void removeNode(int idx) {
		parent[idx] = -2;
		
		for (int i = 0; i < N; i++) {
			if (parent[i] == idx) {
				removeNode(i);
			}
		}
	}
	
	private static void countLeaf(int idx) {
		boolean leaf = true;
		visited[idx] = true;
		
		if (parent[idx] != -2) {
			for (int i = 0; i < N; i++) {
				if (parent[i] == idx && !visited[i]) {
					countLeaf(i);
					leaf = false;
				}
			}
			
			if (leaf) {
				answer++;
			}
		}
	}

}
