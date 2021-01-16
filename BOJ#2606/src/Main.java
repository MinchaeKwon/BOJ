/**
 * 2606 - 바이러스
 * https://www.acmicpc.net/problem/2606
 * 
 * @author minch
 * @date 2021.1.16
 * 
 * 입력
 * 첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다.
 * 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다.
 * 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.
 * 
 * 출력
 * 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //컴퓨터의 수 -> 정점 개수라고 생각
		int v = sc.nextInt(); //연결되어 있는 컴퓨터 쌍의 수 -> 간선 개수라고 생각
		
		LinkedList<Integer>[] list = new LinkedList[n + 1];
		boolean[] visited = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < v; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
			list[b].add(a);
		}
		
		//dfs나 bfs로 탐색하면서 1번 컴퓨터와 네트워크에 연결된 컴퓨터 개수를 셈
		
		sc.close();

	}

}
