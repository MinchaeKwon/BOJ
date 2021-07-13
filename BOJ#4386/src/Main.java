/**
 * 4386 별자리 만들기
 * https://www.acmicpc.net/problem/4386
 * 
 * @author minchae
 * @date 2021. 7. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int s;
	int e;
	double weight;
	
	public Edge(int s, int e, double weight) {
		this.s = s;
		this.e = e;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		// 가중치를 기준으로 오름차순 정렬
		return Double.compare(this.weight, e.weight);
	}
}

public class Main {
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		double[][] star = new double[n][2]; // 별들의 좌표 저장
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < n - 1; i++) {			
			for (int j = i + 1; j < n; j++) {
				double x1 = star[i][0];
				double y1 = star[i][1];
				double x2 = star[j][0];
				double y2 = star[j][1];
				
				double weight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
				
				pq.add(new Edge(i, j, weight));
			}
		}
		
		parent = new int[n];
		
		// 부모를 자기 자신으로 초기화
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		double result = 0;
		
		// 크루스칼 알고리즘 이용
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			// 별자리의 시작점과 끝점의 최상위 노드를 찾음
			int rootS = find(edge.s);
			int rootE = find(edge.e);
			
			// 최상위 노드가 같지 않을 경우 union
			if (rootS != rootE) {
				union(rootS, rootE);
				result += edge.weight; // 가중치를 더함
			}
		}
		
		System.out.println(String.format("%.2f", result));
		
	}
	
	// 별 x가 속하는 부모 노드(최상위 노드)를 찾음
	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		else {
			return parent[x] = find(parent[x]);
		}
	}
	
	// 두 개의 별이 속한 집합을 합침(연결함)
	public static void union(int x, int y) {		
		parent[x] = y;
	}

}
