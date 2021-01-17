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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[] computer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
		}
		
		//각 컴퓨터(정점)별로 해킹할 수 있는 컴퓨터 수를 셈 -> 그 중에 최대값을 가진 컴퓨터를 출력
		computer = new int[N + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			dfs(i, visited, list);
		}
		
		//최대로 해킹할 수 있는 컴퓨터 수를 구함
		for (int i = 1; i <= N; i++) {
			System.out.println("max - " + max + ", result - " + computer[i]);
			max = Math.max(max, computer[i]);
		}
		
		//가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터가 여러개일수도 있기 때문에 for문을 돌면서 각 컴퓨터 번호를 출력
		for (int i = 1; i <= N; i++) {
			if (computer[i] == max) {
				bw.write(i + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int start, boolean[] visited, ArrayList<Integer>[] list) {
		visited[start] = true;

		for (int node : list[start]) {
			if (!visited[node]) {
				computer[node]++;
				dfs(node, visited, list);
			}
		}
		
	}

}
