/**
 * 20040 사이클 게임
 * https://www.acmicpc.net/problem/20040
 * 
 * @author minchae
 * @date 2022. 4. 11.
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
		
        // i를 유일한 원소로 가지는 집합 생성
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
		
        int result = 0;
        
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 두 점의 번호의 부모가 같은 경우는 사이클이 형성된 것
            if (find(a) == find(b)) {
            	result = i + 1;
            	break;
            } else {
            	union(a, b);
            }
		}

		System.out.println(result);
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
	
	// x가 속하는 부모 원소(최상위 원소)를 찾음
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        else {
            return parent[x] = find(parent[x]);
        }
    }

}
