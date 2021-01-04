/* 11437 - LCA(가장 가까운 공통 조상 찾기)
 * 2021.1.4
 * 
 * 문제
 * N(2 ≤ N ≤ 50,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
 * 두 노드의 쌍 M(1 ≤ M ≤ 10,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
 * 
 * 입력
 * 첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.
 * 
 * 출력
 * M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static LinkedList<Integer>[] list;
	static int[] depth; //트리 깊이
	static int[] parent; //부모 노드 번호
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		list = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0;i < N - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
			list[b].add(a);
		}
		
		parent = new int[N + 1];
		depth = new int[N + 1];
		
		dfs(1, 1); //1이 루트노드이기 때문에 1부터 시작 -> 노드들의 깊이와 부모노드를 알아내기 위해 dfs 사용
		
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			System.out.println(findLCA(a, b));
		}
		
		sc.close();
	}
	
	public static void dfs(int v, int d) { //정점, 깊이
		depth[v] = d;
		
		LinkedList<Integer> child = list[v]; //각 정점에 연결된 노드들을 가져옴
		for (int i = 0; i < child.size(); i++) {
			int node = child.get(i);
			
			if (depth[node] == 0) { //깊이가 0이라는 것은 아직 깊이 계산이 안된 상태 -> 새로운 자식노드를 발견한 것
				dfs(node, d + 1);
				parent[node] = v;
			}
		}
		
//		for (int child : list[v]) {
//			if (depth[child] == 0) {
//				dfs(child, d + 1);
//				parent[child] = v;
//			}
//		}
		
	}
	
	public static int findLCA(int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
		
		//두 노드중 더 깊은 곳에 있는 노드의 깊이를 1씩 줄여가면서 같은 깊이로 올려줌
		if (depthA < depthB) { //노드 b가 더 깊을 경우에 두개의 깊이가 같아질때까지 b를 노드 a와 같은 깊이로 올림
			while (depthA != depthB) {
				b = parent[b]; //깊이를 올리기때문에 b의 부모노드를 찾아서 부모노드 값으로 바꿔줌
				depthB--;
			}
		} else if (depthA > depthB) {
			while (depthA != depthB) {
				a = parent[a];
				depthA--;
			}
		}
		
		//깊이가 같은데 부모노드가 다를 경우 공통 부모를 만날 때까지 루프를 돌면서 공통 부모를 찾음
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

}
