/**
 * 11403 - 경로 찾기
 * https://www.acmicpc.net/problem/11403
 * 
 * @author Minchae Gwonn
 * @date 2021.1.23
 * 
 * 문제
 * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. 
 * i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.
 * 
 * 출력
 * 총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n + 1][n + 1];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//bfs
//		for (int i = 0; i < n; i++) {
//			bfs(i, map, n);
//		}
		
		//플로이드 워샬
		floyd(n, map);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//bfs로 해당 정점에서 다른 모든 정점으로의 경로가 있는지 없는지 확인
	public static void bfs(int start, int[][] map, int n) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		
		q.add(start);
//		자기 자신으로 가는 경로도 1이기 때문에 visited[start] = true는 여기서 하지 않아도 됨
		
		while (!q.isEmpty()) {
			int tmp = q.poll();
			
			for (int i = 0; i < n; i++) {
				if (map[tmp][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					map[start][i] = 1;
				}
			}
		}

	}
	
	//플로이드 워샬
	public static void floyd(int n, int[][] map) {
		for (int k = 0; k < n; k++) { //k는 거쳐가는 정점
			for (int i = 0; i < n; i++) { //i는 시작 정점
				for (int j = 0; j < n; j++) { //j는 도착 정점
					//최단 경로를 구하는 것이 아니라 출발 정점에서 도착 정점까지의 경로가 있는지만을 확인하기 때문에 1인지만 보면 됨
					if (map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					}
				}
			}
		}
	}

}
