/**
 * 6118 숨바꼭질
 * https://www.acmicpc.net/problem/6118
 * 
 * @author minchae
 * @date 2023. 12. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Barn {
	int num;
	int d;
	
	public Barn(int num, int d) {
		this.num = num;
		this.d = d;
	}
}

public class Main {
	
	static int N, M;
	static ArrayList<Integer>[] list;
	
	static int[] distance;
	static boolean[] visited;
	
	static int maxDist = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		distance = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 모든 헛간은 양방향 길로 이어져 있기 때문에 둘 다 연결해줌
			list[a].add(b);
			list[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
		}

		bfs();
		
		int maxNum = 0;
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			if (distance[i] == maxDist) {
				// 거리가 같은 헛간이 여러 개일 경우 번호가 가장 작은 것을 출력해야 하기 때문에 0일 때만 값을 넣어줌
				if (maxNum == 0) {
					maxNum = i;
				}
				
				cnt++;
			}
		}
		
		System.out.println(maxNum + " " + maxDist + " " + cnt);
	}
	
	private static void bfs() {
		Queue<Barn> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(new Barn(1, 0));
		visited[1] = true;
		
		while (!q.isEmpty()) {
			Barn cur = q.poll();
			
			maxDist = Math.max(maxDist, cur.d);
			
			for (int next : list[cur.num]) {
				if (!visited[next]) {
					q.add(new Barn(next, cur.d + 1));
					visited[next] = true;
					
					distance[next] = cur.d + 1;
				}
			}
		}
	}

}
