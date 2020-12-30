//11725 - 트리의 부모 찾기(DFS 이용)
/* 
 * 문제
 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
 * 
 * 출력
 * 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
*/

import java.util.ArrayList;
import java.util.Scanner;

class Node {
	int node;
	Node parent;
	
	public Node(int node) {
		this.node = node;
	}
}

public class Main {

	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int[] parent;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		tree = new ArrayList[n+1];
		parent = new int[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		//루트 노트가 1이기 때문에 1부터 dfs를 시작함
		dfs(1);
		
		for (int i = 2; i <= n; i++) {
			System.out.println(parent[i]);
		}
		
		sc.close();

	}
	
	private static void dfs(int v) {
		visited[v] = true;
		
		//현재 노드와 연결되어 있는 노드를 가져와서 방문한 적이 없다면 현재 노드가 부모 노드가 됨
		//그리고 현재 노드에 연결되어 있는 노드가 자식 노드가 됨(자식 노드보다 부모 노드를 먼저 방문함)
		
		for(int i : tree[v]) { //처음에는 1에 연결된 노드들을 방문하면서 부모 노드를 찾아감
			if(!visited[i]) {
				parent[i] = v;
				dfs(i);
			}
		}
	}

}
