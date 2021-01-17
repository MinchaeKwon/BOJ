/**
 * 1325 - 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 * 
 * @author Minchae Gwon
 * @date 2021.1.17
 * 문제
 * 해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다.
 * 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
 * 이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
 * 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다.
 * 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
 * 
 * 출력
 * 첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	
	static int[] computer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		LinkedList<Integer>[] list = new LinkedList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
//			list[b].add(a);
		}
		
		//각 컴퓨터(정점)별로 해킹할 수 있는 컴퓨터 수를 셈 -> 그 중에 최대값을 가진 컴퓨터를 출력
		computer = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			dfs(i, visited, list);
		}
		
		//최대로 해킹할 수 있는 컴퓨터 수를 구함
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			if (computer[i] > max) {
				max = computer[i];
			}
		}
		
		//가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터가 여러개일수도 있기 때문에 for문을 돌면서 각 컴퓨터 번호를 출력
		for (int i = 0; i <= N; i++) {
			if (max == computer[i]) {
				System.out.print(i + " ");
			}
		}
		
		sc.close();

	}
	
	public static void dfs(int start, boolean[] visited, LinkedList<Integer>[] list) {
		visited[start] = true;
		
		for (int node : list[start]) {
			if (!visited[node]) {
				dfs(node, visited, list);
				computer[start]++;
			}
		}
		
	}

}
