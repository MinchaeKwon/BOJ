/**
 * 1260 - DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 * 
 * @author Minchae Gwon
 * @date 2021.1.16
 * 
 * 문제
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
 * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
 * 
 * 입력
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
 * 
 * 출력
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //정점 개수
		int m = sc.nextInt(); //간선 개수
		int v = sc.nextInt(); //탐색을 시작할 정점 번호
		
		//인접리스트로 구현
		LinkedList<Integer>[] list = new LinkedList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
			list[b].add(a);
		}
		
		//한 노드에 연결된 정점이 여러개일때 정점 번호가 작은 것부터 방문하기 위해 정렬함 -> 오름차순
		for (int i = 1; i <= n; i++) {
			Collections.sort(list[i]);	
		}
		
		boolean[] visited = new boolean[n + 1];
		dfs(v, list, visited);
		
		Arrays.fill(visited, false); //dfs를 하면서 visited가 true가 되었기때문에 false로 초기화 해줘야함
		System.out.println();
		
		bfs(v, list, visited);
		
		//인접행렬로 구현
		/*
		int[][] map = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		boolean[] visited = new boolean[n + 1];
		dfs(v, map, visited);
		
		Arrays.fill(visited, false);
		System.out.println();
		
		bfs(v, map, visited);
		*/
		
		sc.close();

	}
	
	//인접리스트
	public static void dfs(int v, LinkedList<Integer>[] list, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		
		for (int node : list[v]) {
			if (!visited[node]) {
				dfs(node, list, visited);
			}
		}
	}
	
	public static void bfs(int v, LinkedList<Integer>[] list, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(v);
		visited[v] = true;
		
		while (!queue.isEmpty()) {
			v = queue.poll();
			System.out.print(v + " ");
			
			for (int node : list[v]) {
				if (!visited[node]) {
					visited[node] = true;
					queue.add(node);
				}	
			}
		}
	}
	
	//인접행렬
	public static void dfs(int v, int[][] map, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		
		for (int i = 1; i < visited.length; i++) {
			if (map[v][i] == 1 && !visited[i]) {
				dfs(i, map, visited);
			}
		}
	}
	
	public static void bfs(int v, int[][] map, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(v);
		visited[v] = true;
		
		while (!queue.isEmpty()) {
			v = queue.poll();
			System.out.print(v + " ");
			
			for (int i = 1; i < visited.length; i++) {
				if (map[v][i] == 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
	}

}
