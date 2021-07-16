/**
 * 1717 집합의 표현
 * https://www.acmicpc.net/problem/1717
 * 
 * @author minchae
 * @date 2021. 7. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		
		// i를 유일한 원소로 가지는 집합 생성 (make set)
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 0이면 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합침
			if (command == 0) {
				union(a, b);
			}
			else { // 1이면 두 원소가 같은 집합에 포함되어 있는지를 확인
				// 부모가 같으면 같은 집합에 포함되어 있는 것
				if (find(a) == find(b)) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		}

	}
	
	// x가 속하는 집합에서 최상위 원소(부모 원소)를 찾음
	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		else {
			return parent[x] = find(parent[x]);
		}
	}
	
	// 두 개의 원소가 속한 집합을 합침
	public static void union(int x, int y) {
		// 각 원소의 최상위 원소를 찾음
		x = find(x);
		y = find(y);
		
		// 최상위 원소가 같지 않을 경우 union
		if (x != y) {
			parent[x] = y;
		}
	}

}
