/**
 * 1976 여행 가자
 * https://www.acmicpc.net/problem/1976
 * 
 * @author minchae
 * @date 2023. 12. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				int connection = Integer.parseInt(st.nextToken());
				
				// i번 도시와 j번 도시가 연결되어 있는 경우 union
				if (connection == 1) {
					union(i, j);
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = find(Integer.parseInt(st.nextToken()));
		
		for (int i = 0; i < M - 1; i++) {
			int num = find(Integer.parseInt(st.nextToken()));
			
			// 두 도시의 부모 노드가 같지 않은 것은 서로 연결되어 있지 않은 것 -> 여행경로 만들 수 없음
			if (start != num) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");

	}
	
	// 노드 x가 속하는 부모 노드를 찾음
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
	
    // 두 개의 노드가 속한 집합을 합침(연결함)
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
    	
        // 최상위 노드가 같지 않을 경우 union
        if (x != y) {
            parent[y] = x;
        }
		
        // 작은 숫자가 부모 노드가 되게 하고 큰 숫자가 자식 노드가 되게 함
//		if (x < y) {
//			parent[y] = x;
//		} else {
//			parent[x] = y;
//		}
    }

}
