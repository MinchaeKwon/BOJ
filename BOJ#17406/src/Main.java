/**
 * 17406 배열 돌리기 4
 * https://www.acmicpc.net/problem/17406
 * 
 * @author minchae
 * @date 2023. 10. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	
	static int[][] A;
	static int[][] rotate;
	
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][M];
		rotate = new int[K][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			rotate[i][0] = Integer.parseInt(st.nextToken()) - 1;
			rotate[i][1] = Integer.parseInt(st.nextToken()) - 1;
			rotate[i][2] = Integer.parseInt(st.nextToken());
		}
		
		backtracking(0, new int[K], new boolean[K]);
		
		System.out.println(min); // 최솟값 출력
	}
	
	// 순열 이용
	private static void backtracking(int depth, int[] arr, boolean[] visited) {
		if (depth == K) {
			rotateMap(arr);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				arr[depth] = i;
				visited[i] = true;
				
				backtracking(depth + 1, arr, visited);
				
				visited[i] = false;
			}
		}
	}
	
	private static void rotateMap(int[] order) {
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(A[i], M);
		}
		
		for (int k = 0; k < K; k++) {
			int r = rotate[order[k]][0];
			int c = rotate[order[k]][1];
			int S = rotate[order[k]][2];
			
			// 한 칸씩 회전
			for (int s = 1; s <= S; s++) {
				// 위
				int upTmp = copy[r - s][c + s];
				
				for (int y = c + s; y > c - s; y--) {
					copy[r - s][y] = copy[r - s][y - 1];
				}
				
				// 오른쪽
				int rightTmp = copy[r + s][c + s];
				
				for (int x = r + s; x > r - s; x--) {
					copy[x][c + s] = copy[x - 1][c + s];
				}
				
				copy[r - s + 1][c + s] = upTmp;
				
				// 아래
				int downTmp = copy[r + s][c - s];
				
				for (int y = c - s; y < c + s; y++) {
					copy[r + s][y] = copy[r + s][y + 1];
				}
				
				copy[r + s][c + s - 1] = rightTmp;
				
				// 왼쪽
				for (int x = r - s; x < r + s; x++) {
					copy[x][c - s] = copy[x + 1][c - s];
				}
				
				copy[r + s - 1][c - s] = downTmp;
			}
		}
		
		getMinValue(copy); // 배열을 회전시켰으니까 최솟값 찾음
	}
	
	// 각 행에 있는 모든 수의 합 중 최솟값 찾기
	private static int getMinValue(int[][] copy) {		
		for (int i = 0; i < N; i++) {
			int sum = 0;
			
			for (int j = 0; j < M; j++) {
				sum += copy[i][j];
			}
			
			min = Math.min(min, sum);
		}
		
		return min;
	}

}
