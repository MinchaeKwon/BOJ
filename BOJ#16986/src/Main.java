/**
 * 16986 인싸들의 가위바위보
 * https://www.acmicpc.net/problem/16986
 * 
 * @author minchae
 * @date 2024. 4. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[][] map;
	static int[][] round;
	
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		round = new int[4][21];
		visited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 2; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= 20; j++) {
				round[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		permulation(1);
		System.out.println(0);
	}
	
	// 순열을 이용해 지우의 손동작 구함
	private static void permulation(int depth) {
		if (depth == N + 1) {
			if (play()) {
				System.out.println(1);
				System.exit(0);
			}
			
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				round[1][depth] = i;
				permulation(depth + 1);
				
				visited[i] = false;
			}
		}
	}
	
	private static boolean play() {
		int[] winCnt = new int[4];
		int[] index = new int[4];
		
		Arrays.fill(index, 1);
		
		int p1 = 1;
		int p2 = 2;
		int p3 = 3;
		
		while (true) {
			// 지우가 이긴 경우
			if (winCnt[1] == K) {
				return true;
			}
			
			// 경희 또는 민호가 이긴 경우
			if (winCnt[2] == K || winCnt[3] == K) {
				return false;
			}
			
			// 특정 플레이어가 가지고 있는 패를 다 쓴 경우
			if (index[1] == N + 1 || index[2] == 21 || index[3] == 21) {
				return false;
			}
			
			p3 = 6 - p1 - p2;
			
			int winner = getWinner(p1, p2, index);
			
			winCnt[winner]++;
			index[p1]++;
			index[p2]++;
			
			p1 = winner;
			p2 = p3;
		}
	}
	
	private static int getWinner(int p1, int p2, int[] index) {
		int action1 = round[p1][index[p1]];
		int action2 = round[p2][index[p2]];
		
		if (map[action1][action2] == 2) {
			return p1;
		} else if (map[action1][action2] == 0) {
			return p2;
		} else {
			return Math.max(p1, p2); // 비긴 경우 진행 순서 상 뒤인 사람이 승리
		}
	}

}
