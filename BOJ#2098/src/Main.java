/**
 * 2098 외판원 순회
 * https://www.acmicpc.net/problem/2098
 * 
 * @author minchae
 * @date 2024. 4. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = 16_000_000;
	
	static int N;
	
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][(1 << N) - 1]; // 특정 도시에서 다른 도시까지의 경로의 비용 저장
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 1));
	}
	
	private static int dfs(int now, int visit) {

		// 모든 도시를 지난 경우
		if (visit == (1 << N) - 1) {
			// now -> 0(출발도시)로 가는 경로가 존재해야 돌아갈 수 있음
			if (map[now][0] == 0)
				return INF;
			return map[now][0];
		}

		if (dp[now][visit] != -1) {
			return dp[now][visit];
		}
			
		dp[now][visit] = INF;

		for (int i = 0; i < N; i++) {
			// i를 방문하지 않았고, now에서 i로 가는 경로가 있는 경우
			if ((visit & (1 << i)) == 0 && map[now][i] != 0) {
				// 최소비용 갱신
				dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + map[now][i], dp[now][visit]);
			}
		}
		
		return dp[now][visit];
	}

}
