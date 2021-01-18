/**
 * 5567 - 결혼식
 * https://www.acmicpc.net/problem/5567
 * 
 * @author Minchae Gwon
 * @date 2021.1.18
 * 
 * 문제
 * 상근이는 자신의 결혼식에 학교 동기 중 자신의 친구와 친구의 친구를 초대하기로 했다. 상근이의 동기는 모두 N명이고, 이 학생들의 학번은 모두 1부터 N까지이다. 상근이의 학번은 1이다.
 * 상근이는 동기들의 친구 관계를 모두 조사한 리스트를 가지고 있다. 이 리스트를 바탕으로 결혼식에 초대할 사람의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 상근이의 동기의 수 n (2 ≤ n ≤ 500)이 주어진다. 둘째 줄에는 리스트의 길이 m (1 ≤ m ≤ 10000)이 주어진다.
 * 다음 줄부터 m개 줄에는 친구 관계 ai bi가 주어진다. (1 ≤ ai < bi ≤ n) ai와 bi가 친구라는 뜻이며, bi와 ai도 친구관계이다. 
 * 
 * 출력
 * 첫째 줄에 상근이의 결혼식에 초대하는 동기의 수를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		LinkedList<Integer>[] list = new LinkedList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		boolean[] visited = new boolean[n + 1];
		int friend = 0;
		
		System.out.println(bfs(1, friend, visited, list));

	}
	
	public static int bfs(int start, int friend, boolean[] visited, LinkedList<Integer>[] list) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			start = q.poll();
			
			for (int node : list[start]) {
				if (start == 1) { //친구의 친구까지만 초대하므로 상근이의 친구까지만 큐에 넣음 -> 이외의 사람들은 큐에 넣을 필요 없음
					q.add(node);
				}
				if (!visited[node]) {
					visited[node] = true;
					friend++;
				}
			}
		}
		
		return friend;
	}

}
