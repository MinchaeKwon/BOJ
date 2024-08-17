/**
 * 1826 연료 채우기
 * https://www.acmicpc.net/problem/1826
 * 
 * @author minchae
 * @date 2024. 8. 17.
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int dist;
		int fuel;
		
		public Node(int dist, int fuel) {
			this.dist = dist;
			this.fuel = fuel;
		}

		// 거리순으로 오름차순 정렬 (가까운 주유소부터 들려야 하기 때문)
		@Override
		public int compareTo(Main.Node o) {
			return this.dist - o.dist;
		}
	}

	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	static int town; // 마을까지의 거리
	static int cur; // 현재 연료 양
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(a, b));
		}
		
		st = new StringTokenizer(br.readLine());
		
		town = Integer.parseInt(st.nextToken());
		cur = Integer.parseInt(st.nextToken());
		
		System.out.println(solve());
	}
	
	private static int solve() {
		// 연료를 가장 많이 채울 수 있도록 내림차순 정렬
		PriorityQueue<Integer> fuelQ = new PriorityQueue<>(Collections.reverseOrder());
		
		int answer = 0; // 방문하는 주유소 개수
		
		// 마을에 도달하면 종료
		while (cur < town) {
			// 주유소가 남아있고, 현재 연료로 갈 수 있는 경우
			while (!pq.isEmpty() && pq.peek().dist <= cur) {
				fuelQ.add(pq.poll().fuel); // 해당 주유소의 연료 추가
			}
			
			// 연료가 모자라서 주유소에 갈 수 없는 경우 -> 마을에 도달하지 못함
			if (fuelQ.isEmpty()) {
				return -1;
			}
			
			answer++;
			cur += fuelQ.poll();
		}
		
		return answer;
	}

}
