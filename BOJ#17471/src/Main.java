/**
 * 17471 게리맨더링
 * https://www.acmicpc.net/problem/17471
 * 
 * @author minchae
 * @date 2023. 12. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] population;
	
	static ArrayList<Integer>[] map;
	static boolean[] selected;
	static int total;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N + 1];
		map = new ArrayList[N + 1];
		selected = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			total += population[i];
			
			map[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cnt = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			
			for (int j = 0; j < cnt; j++) {
				map[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		comb(0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	// 구역을 두 개의 선거구로 나눔
	private static void comb(int depth) {
		if (depth == N) {
			ArrayList<Integer> listA = new ArrayList<>();
			ArrayList<Integer> listB = new ArrayList<>();
			
			for (int i = 1; i <= N; i++) {
				if (selected[i]) {
					listA.add(i);
				} else {
					listB.add(i);
				}
			}
			
			if (listA.size() == 0 || listB.size() == 0) {
				return;
			}
			
			if (isConnected(listA) && isConnected(listB)) {
				int diff = getDiff();
				
				answer = Math.min(answer, diff);
			}
			
			return;
		}
		
		selected[depth] = true;
		comb(depth + 1);
		
		selected[depth] = false;
		comb(depth + 1);
	}
	
	// 특정 선거구의 지역이 모두 연결되어 있는지 확인 (BFS)
	private static boolean isConnected(ArrayList<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		int start = list.get(0);
		
		q.add(start);
		visited[start] = true;
		
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : map[cur]) {
				if (!visited[next] && list.contains(next)) {
					q.add(next);
					visited[next] = true;
					
					cnt++;
				}
			}
		}
		
		return cnt == list.size();
	}
	
	// 두 선거구의 인구 차이 구하기
	private static int getDiff() {
		int a = 0;
		
		for (int i = 1; i <= N; i++) {
			if (selected[i]) {
				a += population[i];
			}
		}
		
		return Math.abs(a - (total - a));
	}

}
