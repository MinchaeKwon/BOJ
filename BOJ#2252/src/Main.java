//줄 세우기 - 위상정렬(DFS)

//import java.util.ArrayList;
//import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;

public class Main {
	private static Stack<Integer> stack = new Stack<>();
//	private static List<Integer> resultList;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 정점의 개수
		int m = sc.nextInt(); // 간선의 개수
		
		LinkedList<Integer>[] list = new LinkedList[n + 1];
//		resultList = new ArrayList<Integer>();

		boolean[] visited = new boolean[n + 1]; /// 방문 check

		for (int i = 1; i <= n; i++)
			list[i] = new LinkedList<Integer>();

		for (int i = 0; i < m; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			list[v1].add(v2);
		}

		topologicalSort(list, visited, n);

		// 스택 사용하면 바로 역순으로 출력 가능
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");

		// 리스트 역순으로 출력
//        for (int i = n - 1; i >= 0; i--)
//        	System.out.print(resultList.get(i) + " ");

		sc.close();
	}

	public static void topologicalSort(LinkedList<Integer>[] list, boolean[] visited, int n) {
		for (int i = 1; i <= n; i++)
			visited[i] = false;

		for (int i = 1; i <= n; i++)
			if (!visited[i])
				dfs(list, visited, i);
	}

	public static void dfs(LinkedList<Integer>[] list, boolean[] visited, int v) {
		visited[v] = true;

		for (int k : list[v])
			if (!visited[k])
				dfs(list, visited, k);
		
		// 위상정렬 완료 순서의 역순으로 정점들이 들어가게됨 -> 스택을 사용해서 pop하면 역순으로 출력되기 때문에 스택 사용함
		stack.push(v);
//      resultList.add(v);
	}

}
