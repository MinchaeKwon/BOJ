/**
 * 21940 가운데에서 만나기
 * https://www.acmicpc.net/problem/21940
 * 
 * @author minchae
 * @date 2024. 10. 21.
 * */

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 987654321;
	
	static int N, M, K;
	static int[][] map;
	static int[] person;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], INF);	
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int T = Integer.parseInt(st.nextToken());
			
			map[A][B] = T;
		}
		
		K = Integer.parseInt(br.readLine());
		
		person = new int[K];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			person[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		floyd();
	}
	
	private static void floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		ArrayList<Integer> candidate = new ArrayList<>();
		int min = INF; // 왕복시간들 중 최대가 최소가 되는 값 저장
		
		for (int i = 0; i < N; i++) {
			int sum = 0; // 해당 정점에서 친구들과의 거리 중 최댓값 저장
			
			for (int num : person) {
				// 왕복 시간 구하기
				int go = map[i][num];
				int back = map[num][i];
				
				// 경로가 없는 경우 다음으로 넘어감
				if (go == INF || back == INF) {
					continue;
				}
				
				// 최댓값 저장
				sum = Math.max(sum, go + back);
			}
			
			if (sum < min) { // 더 작은 값을 발견한 경우 리스트 초기화 후 추가
				candidate.clear();
				candidate.add(i + 1);
				
				min = sum; // 갱신
			} else if (sum == min) { // 현재 최솟값과 같은 경우 리스트에 추가
				candidate.add(i + 1);
			}
		}
		
		for (int num : candidate) {
			System.out.print(num + " ");
		}
	}

}