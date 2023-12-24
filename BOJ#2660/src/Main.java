/**
 * 2660 회장뽑기
 * https://www.acmicpc.net/problem/2660
 * 
 * @author minchae
 * @date 2023. 12. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 987654321;
	
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		
		// 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N ; j++) {
				if (i != j) {
					map[i][j] = INF;
				}
			}
		}
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (x == -1 && y == -1) {
				break;
			}
			
			// 친구 관계는 양방향
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		// 플로이드 워셜 알고리즘 수행
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int minScore = INF; // 회장 후보의 점수
		
		int[] score = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			int tmp = 0;
			
			for (int j = 1; j <= N; j++) {
				// 서로 연결되어 있는 경우에만 계산
				if (map[i][j] != INF) {
					tmp = Math.max(tmp, map[i][j]);	
				}
			}
			
			score[i] = tmp;
			minScore = Math.min(minScore, tmp); // 최솟값 갱신
		}
		
		
		int cnt = 0; // 회장 후보의 수
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			if (score[i] == minScore) {
				cnt++;
				sb.append(i + " "); // 회장 후보 저장
			}
		}
		
		System.out.println(minScore + " " + cnt);
		System.out.println(sb.toString());
	}

}
