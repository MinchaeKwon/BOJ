/**
 * 15686 치킨 배달
 * https://www.acmicpc.net/problem/15686
 * 
 * @author minchae
 * @date 2024. 2. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map; // 0은 빈 칸, 1은 집, 2는 치킨집
	
	static boolean[] visited;
	
	static ArrayList<Pair> home = new ArrayList<>(); // 집 저장
	static ArrayList<Pair> chicken = new ArrayList<>(); // 치킨집 저장
	
	static ArrayList<Pair> pick = new ArrayList<>(); // 선택된 M개의 치킨집
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					home.add(new Pair(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Pair(i, j));
				}
			}
		}
		
		select(0, 0);

		System.out.println(answer);
	}
	
	// M개의 치킨집 고르기
	private static void select(int depth, int start) {
		if (depth == M) {
			getDistance();
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			Pair p = chicken.get(i);
			
			pick.add(new Pair(p.x, p.y));
			select(depth + 1, i + 1);
			pick.remove(pick.size() - 1);
		}
		
	}
	
	private static void getDistance() {
		int total = 0;
		
		for (Pair h : home) {
			int min = Integer.MAX_VALUE;
			
			// 특정 집과 가장 가까운 치킨집 사이의 거리 구하기
			for (Pair p : pick) {
				int dist = Math.abs(h.x - p.x) + Math.abs(h.y - p.y);
				min = Math.min(dist, min); // M개의 치킨집 중에서 치킨 거리가 가장 짧은 곳 구함
			}
			
			total += min; // 각 집의 치킨거리 합
		}
		
		answer = Math.min(answer, total); // 최솟값 갱신
	}

}
