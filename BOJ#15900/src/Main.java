/* 
 * 15900 - 나무 탈출
 * 
 * 입력
 * 첫째 줄에 트리의 정점 개수 N(2 ≤ N ≤ 500,000)이 주어진다.
 * 둘째 줄부터 N-1줄에 걸쳐 트리의 간선 정보가 주어진다. 줄마다 두개의 자연수 a, b(1 ≤ a, b ≤ N, a ≠ b)가 주어지는데, 이는 a와 b 사이에 간선이 존재한다는 뜻이다.
 * 
 * 출력
 * 성원이가 최선을 다했을 때 이 게임을 이길 수 있으면 Yes, 아니면 No를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static LinkedList<Integer>[] list;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		list = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		visited = new boolean[n + 1];
		result = 0;
		findWin(1, 0);

		//루트노드에서 각 리프노드까지의 총 경로합이 홀수일 경우에만 성원이가 승리함
		if (result % 2 == 0)
			System.out.println("No");
		else
			System.out.println("Yes");
		
		br.close();
	}
	
	public static void findWin(int node, int edge) {
		visited[node] = true;

		//루트노드에서 각 리프노드까지의 경로합을 구함
		for (int v : list[node]) {
			if (!visited[v]) {
				findWin(v, edge + 1); //각 노드를 방문하면서 경로를 1 증가시킴
			}
		}
		
		//루트노드가 아니거나 노드의 리스트 크기가 1인 경우는 리프노드임(리프노드는 연결된 노드가 자신의 부모노드밖에 없기때문에 연결리스트의 크기가 1이다)
		if (node != 1 && list[node].size() == 1) {
			result += edge; //총 경로합을 얻음
		}
	}

}
