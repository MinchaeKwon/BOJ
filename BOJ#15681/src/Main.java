/*
 * 15681 - 트리와 쿼리
 * 2021.1.6
 * 
 * Scanner로 입력받으니까 메모리초과 뜸
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static LinkedList<Integer>[] list;
	static int[] dp;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		list = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		dp = new int[n + 1];
		parent = new int[n + 1];
		
		makeTree(r, -1);
		countSubtreeNode(r);
		
		//서브트리에 속한 정점의 수를 알고싶은 정점을 입력받아 출력함
		for (int i = 0; i < q; i++) {
			int u=Integer.parseInt(br.readLine());
			System.out.println(dp[u]);
		}
		
		br.close();
	}
	
	public static void makeTree(int cur, int prev) {
		parent[cur] = prev; //각 노드의 부모노드를 저장함
		
		for (int child : list[cur]) {
			if (child != prev) {
				makeTree(child, cur);
			}
		}
	}
	
	public static void countSubtreeNode(int cur) {
		dp[cur] = 1; //자신도 자신을 루트로 하는 서브트리에 포함되므로 0이 아닌 1로 시작함
	
		for (int child : list[cur]) {
			if (child != parent[cur]) { //makeTree에서 parent배열에 각 노드의 부모노드를 저장한걸 이용함
				countSubtreeNode(child);
				dp[cur] += dp[child];	
			}
		}
	}

}
