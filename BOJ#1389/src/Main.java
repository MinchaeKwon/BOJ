/**
 * 1389 - 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 * 
 * @author Minchae Gwon
 * @date 2021.1.21
 * 
 * 입력
 * 첫째 줄에 유저의 수 N (2 ≤ N ≤ 100)과 친구 관계의 수 M (1 ≤ M ≤ 5,000)이 주어진다.
 * 둘째 줄부터 M개의 줄에는 친구 관계가 주어진다. 친구 관계는 A와 B로 이루어져 있으며, A와 B가 친구라는 뜻이다.
 * A와 B가 친구이면, B와 A도 친구이며, A와 B가 같은 경우는 없다. 친구 관계는 중복되어 들어올 수도 있으며, 친구가 한 명도 없는 사람은 없다.
 * 또, 모든 사람은 친구 관계로 연결되어져 있다. 사람의 번호는 1부터 N까지이며, 두 사람이 같은 번호를 갖는 경우는 없다.
 * 
 * 출력
 * 첫째 줄에 BOJ의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 출력한다. 그런 사람이 여러 명일 경우에는 번호가 가장 작은 사람을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

//	static int[][] map;
//	static boolean[][] visited;
//	static int[][] friend; //해당 유저에서 다른 모든 유저까지의 관계 수를 저장
	
	static final int INF = 99999999; // 플로이드 워샬 알고리즘 사용할 때 필요
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n + 1][n + 1];
		int[][] friend = new int[n + 1][n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		//bfs 사용
		for (int i = 1; i <= n; i++) {
			bfs(i, friend, map, n);
			System.out.println();
		}
		System.out.println(getMin(n, friend));
		
		//플로이드 워샬 알고리즘 사용
		floyd(n, map);
		System.out.println(getMin(n, map));

	}

	public static void bfs(int start, int[][] friend, int[][] map, int n) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			//start에서부터 다른 모든 친구(정점)까지의 경로를 구해야하기 때문에 새로운 변수를 사용함
			int y = q.poll();
			
			for (int i = 1; i <= n; i++) {
				//y와 i가 친구 관계이고 방문하지 않은 정점이라면
				if (map[y][i] == 1 && !visited[i]) {
					friend[start][i] = friend[start][y] + 1; //친구 단계를 증가시킴
					q.add(i); //해당 유저(y)와 연결된 다른 유저를 큐에 넣음 -> 방문하지 않은 정점이기 때문에 큐에 삽입
					visited[i] = true;
					
					System.out.println(start + " - " + i + ": " + friend[start][i]);
				}
			}
		}
		
	}
	
	//플로이드 워샬 알고리즘 사용
	public static void floyd(int n, int[][] map) {
		//입력받을 때 값을 비교해서 최소값을 넣는 것이 아니기 때문에 처음에 초기화 하지 않고 먼저 값을 넣은 다음에 값이 들어가지 않은 부분만 INF로 채우면됨
		//경로가 없는 부분은 INF를 넣어줌
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j && map[i][j] == 0) { //자신 자신으로의 경로가 아니거나 경로가 없으면 INF를 넣어줌 
					map[i][j] = INF;
				}
			}
		}
		
		//각각 모든 경로에 대해 최단경로를 만들어줌 -> 1-2, 1-3, 1-4, 2-1, 2-2 ...
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}
	
	//가장 작은 케빈 베이컨의 수를 구함
	public static int getMin(int n, int[][] arr) {
		int min = Integer.MAX_VALUE;
		int result = 0;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				sum += arr[i][j];
			}
			
			if (min > sum) {
				min = sum;
				result = i; //케빈 베이컨의 수가 가장 작은 사람이 답이므로 i를 넣어줌
			}
			
		}
		
		return result;
	}

}
