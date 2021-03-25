/**
 * 15591 MooTube (Gold)
 * https://www.acmicpc.net/problem/15586
 * 
 * @author minchae
 * @date 2021.3.16
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int p;
	int q;
	int usado;

	Node(int p, int q, int usado) {
		this.p = p;
		this.q = q;
		this.usado = usado;
	}

	@Override
	public int compareTo(Node o) {
		return o.usado - this.usado;
	}
}

class Query implements Comparable<Query> {
	int index;
	int usado;
	int n;

	Query(int index, int usado, int n) {
		this.index = index;
		this.usado = usado;
		this.n = n;
	}

	@Override
	public int compareTo(Query o) {
		return o.usado - this.usado;
	}
}

public class Main {
	static int[] parent;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		parent = new int[N + 1]; // 특정 정점의 부모 노드 저장 
		count = new int[N + 1]; // 각 정점이 가지고 있는 추천 동영상 개수 저장 
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			count[i] = 1; // 처음엔 각자 자신만을 가지고 있으니 1로 초기화 
		}

		Node[] node = new Node[N - 1];
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			node[i] = new Node(p, q, r);
		}
		Arrays.sort(node); // 내림차순 정렬 (유사도를 기준으로 함)

		Query[] query = new Query[Q];
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken()); // 유사도 
			int v = Integer.parseInt(st.nextToken());

			query[i] = new Query(i, k, v);
		}
		Arrays.sort(query); // 내림차순 정렬 (유사도를 기준으로 함)

		int[] result = new int[Q];

		int index = 0;
		for(Query q : query) { // 하나의 쿼리에서 추천될 동영상의 개수 찾음 
			// Q에 주어진 유사도보다 큰 것만 확인
			while(index < node.length && node[index].usado >= q.usado) {
				union(node[index].p, node[index].q);
				index++;
			}
			
			result[q.index] = count[find(q.n)] - 1;
		}
 
		for(int r : result) {
			System.out.println(r);
		}

	}

	public static int find(int x) {
		return parent[x] == x ? x : find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[y] = x;
		count[x] += count[y];
	}

}
