/**
 * 15591 MooTube(Silver)
 * https://www.acmicpc.net/problem/15591
 * 
 * @author minchae
 * @date 2021.3.16
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int video; // 특정 정점과 연결된 다른 정점 
	int usado; // 동영상 쌍의 USADO
	
	public Point(int video, int usado) {
		this.video = video;
		this.usado = usado;
	}
}

public class Main {

	static int N, Q;
	static LinkedList<Point>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		list = new LinkedList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			list[p].add(new Point(q, r));
			list[q].add(new Point(p, r));
		}
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken()); // USADO
			int v = Integer.parseInt(st.nextToken()); // 소들이 보고있는 동영상 번호
			
			System.out.println(bfs(k, v));
			
		}
		
	}
	
	public static int bfs(int k, int v) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(v);
		visited[v] = true;
		
		int result = 0;
		while (!q.isEmpty()) {
			v = q.poll();
			
			for (Point p : list[v]) {
				// 특정 정점에 연결된 정점을 방문하지 않았고 유사도가 k보다 큰 경우 동영상 추천될 수 있음
				if (!visited[p.video] && p.usado >= k) { 
					q.add(p.video);
					visited[p.video] = true;
					result++; // 추천 동영상 개수 증가
				}
			}

		}
		
		return result;
	}

}
