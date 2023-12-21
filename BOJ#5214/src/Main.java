/**
 * 5214 환승
 * https://www.acmicpc.net/problem/5214
 * 
 * @author minchae
 * @date 2023. 12. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, M;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 역의 개수
		K = Integer.parseInt(st.nextToken()); // 하이퍼튜브가 연결하는 역의 개수
		M = Integer.parseInt(st.nextToken()); // 하이퍼튜브 개수
		
		list = new ArrayList[N + M + 1]; // 하이퍼튜브 노드로 보기 때문에 크기로 N + M + 1을 해줌
		
		for (int i = 1; i <= N + M; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < K; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				list[num].add(N + i + 1); // 해당 역에 하이퍼튜브 번호 추가
				list[N + i + 1].add(num); // 하이퍼튜브에 역 추가
			}
		}
		
		bfs();
	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + M + 1];
		int[] distance = new int[N + M + 1];
		
		q.add(1);
		visited[1] = true;
		distance[1] = 1;
		
		// 특정 역에서 하이퍼튜브로 이동하고 하이퍼튜브에서 특정 역으로 이동하는걸 반복함
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : list[cur]) {
				if (!visited[next]) {
					q.add(next);
					visited[next] = true;
					distance[next] = distance[cur] + 1;
				}
			}
		}
		
		// 특정 역을 지나갈 때는 무조건 하이퍼튜브를 지나가기 때문에 2로 나눠줌
		System.out.println(visited[N] ? distance[N] / 2 + 1 : -1);
	}

}
