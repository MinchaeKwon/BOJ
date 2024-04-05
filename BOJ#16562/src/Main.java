/**
 * 16562 친구비
 * https://www.acmicpc.net/problem/16562
 * 
 * @author minchae
 * @date 2024. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, k;
	static int[] A;
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		A = new int[N + 1];
		parent = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			union(v, w); // 두 노드의 부모를 합침 -> 해당 노드를 통해 다른 노드와 친구가 될 수 있는 경우를 구하는 것
		}
		
		int sum = 0;
		
		for (int i = 1; i <= N; i++) {
			// 최상위 노드가 자기 자신인 경우 해당 노드를 통해 다른 노드로 갈 수 있는 것
			if (parent[i] == i) {
				sum += A[i];
			}
		}

		if (k - sum < 0) {
			System.out.println("Oh no");
		} else {
			System.out.println(sum);
		}
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y) {
			// 친구비가 더 적은 쪽을 부모로 설정
			if (A[x] > A[y]) {
				parent[x] = y;
			} else {
				parent[y] = x;
			}
		}
	}

}
