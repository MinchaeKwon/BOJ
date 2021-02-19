/**
 * 9084 - 동전
 * https://www.acmicpc.net/problem/9084
 * 
 * @author Minchae Gwon
 * @date 2021.2.20
 * 
 * 입력\입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다. 
 * 각 테스트 케이스의 첫 번째 줄에는 동전의 가지 수 N(1 ≤ N ≤ 20)이 주어지고 두 번째 줄에는 N가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다. 
 * 각 금액은 정수로서 1원부터 10000원까지 있을 수 있으며 공백으로 구분된다. 세 번째 줄에는 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)이 주어진다.
 * 편의를 위해 방법의 수는 231 - 1 보다 작고, 같은 동전이 여러 번 주어지는 경우는 없다.
 * 
 * 출력
 * 각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방법의 수를 한 줄에 하나씩 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] coin = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				coin[j] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			
			//1원부터 M원까지이기 때문에 M + 1 크기로 함
			int[] dp = new int[M + 1];
			//만드려는 금액과 동전의 금액이 같을 경우에 dp[k - coin[j]]을 했을 때 dp[0]이 나올 수 있는데 이때는 무조건 M원을 만들 수 있으므로 0번째 인덱스가 1이여야함
			dp[0] = 1;
			
			for (int j = 0; j < N; j++) {
				//금액이 작은 동전부터 dp배열을 채워넣음, 특정 동전 금액보다 작은 경우는 고려할 필요가 없기 때문에 coin[j]를 시작으로 함
				for (int k = coin[j]; k <= M; k++) {
					dp[k] += dp[k - coin[j]];
				}
			}
			
			System.out.println(dp[M]);
		}
		

	}

}
