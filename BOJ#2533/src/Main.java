//2533 - 사회망 서비스(SNS)
/*
 * 입력
 * 첫 번째 줄에는 친구 관계 트리의 정점 개수 N이 주어진다. 단, 2 <= N <= 1,000,000이며, 각 정점은 1부터 N까지 일련번호로 표현된다.
 * 두 번째 줄부터 N-1개의 줄에는 각 줄마다 친구 관계 트리의 에지 (u, v)를 나타내는 두 정수 u 와 v가 하나의 빈칸을 사이에 두고 주어진다.
 * 
 * 출력
 * 주어진 친구 관계 그래프에서 아이디어를 전파하는데 필요한 얼리 아답터의 최소 수를 하나의 정수로 출력한다. 
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static LinkedList<Integer>[] tree;
	static boolean[] visited;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		tree = new LinkedList[n + 1];
		visited = new boolean[n + 1];
		dp = new int[n + 1][2];
		
		for (int i = 1; i <= n; i++) {
			tree[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		System.out.println(findEarly(1)); //루트노드인 1부터 시작
		
		sc.close();
	}
	
	public static int findEarly(int start) {
		visited[start] = true;
		
		//dp[start][0 or 1]에는 start노드 기준으로 해당 노드가 얼리어답터인지 아닌지에 따라서 필요한 최소 얼리어답터의 수가 저장됨
		dp[start][0] = 0; //얼리어답터가 아닌 경우
		dp[start][1] = 1; //얼리어답터인 경우
		
		LinkedList<Integer> child = tree[start]; //해당 노드에 연결되어 있는 자식노드들을 가져옴
		
		for (int i = 0; i < child.size(); i++) {
			int node = child.get(i);
			
			if (!visited[node]) {
				findEarly(node);
				
				//얼리어답터가 아닌 경우에는 자식노드가 다 얼리어답터가 되어야하기 때문에 dp[node][1]을 더해주는 것
				dp[start][0] += dp[node][1];
				//얼리어답터인 경우에는 자식노드가 얼리어답터여도 되고 아니여도 되니까 dp[node][0]와 dp[node][1] 중 작은 것을 더해줌
				dp[start][1] += Math.min(dp[node][0], dp[node][1]);
			}
		}
		
		return Math.min(dp[start][0], dp[start][1]); //루트노드인 1을 기준으로 그 밑에서 필요한 얼리어답터의 최소 개수를 반환함
	}

}
