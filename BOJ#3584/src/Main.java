/* 3584 - 가장 가까운 공통 조상(11437번과 비슷한 문제)
 * 2021.1.4
 * 
 * 입력
 * 첫 줄에 테스트 케이스의 개수 T가 주어집니다.
 * 각 테스트 케이스마다, 첫째 줄에 트리를 구성하는 노드의 수 N이 주어집니다. (2 ≤ N ≤ 10,000)
 * 그리고 그 다음 N-1개의 줄에 트리를 구성하는 간선 정보가 주어집니다. 한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데, 이는 A가 B의 부모라는 뜻입니다.
 * (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!)
 * A와 B는 1 이상 N 이하의 정수로 이름 붙여집니다.
 * 테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.
 * 
 * 출력
 * 각 테스트 케이스 별로, 첫 줄에 입력에서 주어진 두 노드의 가장 가까운 공통 조상을 출력합니다.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static LinkedList<Integer>[] list;
	static int[] depth; //트리 깊이
	static int[] parent; //부모 노드 번호
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			
			list = new LinkedList[N + 1];
			parent = new int[N + 1];
			depth = new int[N + 1];
			
			for (int j = 1; j <= N; j++) {
				list[j] = new LinkedList<>();
			}
			
			for (int j = 0; j < N - 1; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				list[a].add(b);
				list[b].add(a);
				parent[b] = a; //b의 부모는 a
			}
			
			int root = findRoot(N); //루트가 1이 아니기 때문에 루트를 찾음
			dfs(root, 0, -1); //각 노드의 깊이를 알아냄, 루트노드는 부모노드가 없기때문에 -1을 넣어주는 것
			
			//공통 조상을 찾을 노드 입력
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			System.out.println(findLCA(n1, n2));
		}
		
		sc.close();
	}
	
	public static int findRoot(int n) {
		for (int i = 1; i < n ; i++) {
			if (parent[i] == 0) {
				return i;
			}
		}
		return 0;
	}
	
	//각 노드의 깊이를 알아냄
	public static void dfs(int v, int d, int p) { //정점, 깊이, 부모노드
		depth[v] = d;
		parent[v] = p;
		
		for (int child : list[v]) { //각 정점에 연결된 노드들을 가져옴
			if (child != p) //자식노드가 부모노드가 같은때는 탐색을 하면 안됨
				dfs(child, d + 1, v);
		}
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
