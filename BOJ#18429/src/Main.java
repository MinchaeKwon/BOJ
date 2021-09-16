/**
 * 18429 근손실
 * https://www.acmicpc.net/problem/18429
 * 
 * @author minchae
 * @date 2021. 9. 16
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int K;
	static int[] kit;
	static boolean[] visited;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		kit = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
//		dfs1(0, 0); // 첫 번째 방법
		dfs2(0, 500); // 두 번째 방법
		
		System.out.println(result);

	}
	
	public static void dfs1(int day, int weight) {		
		// 운동 키트를 다 사용한 경우 경우의 수를 증가시키고 종료
		if (day == N) {
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				// 중량이 500이상일 경우에만 호출
				// 0부터 시작했기 때문에 weight가 0보다 커지는 경우가 500이상이 되는 경우임
				if (weight >= 0) {
					dfs1(day + 1, weight + kit[i] - K);	
				}
				
				visited[i] = false;
			}
		}
	}
	
	public static void dfs2(int day, int weight) {
		// 운동 키트를 다 사용한 경우 경우의 수를 증가시키고 종료
		if (day == N) {
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				// 중량이 500이상인 경우에만 호출
				if (weight >= 500) {
					dfs2(day + 1, weight + kit[i] - K);
				}
				
				visited[i] = false;
			}
		}
	}

}
