/**
 * 11404 - 플로이드
 * https://www.acmicpc.net/problem/11404
 * 
 * @author Minchae Gwon
 * @date 2021.1.22
 * 
 * 문제
 * n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다. 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 
 * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 
 * 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
 * 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
 * 
 * 출력
 * n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 99999999;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n + 1][n + 1];
		
		//초기값 넣어줌
		//INF로 초기화를 해야 경로를 입력받는 부분에서 현재 저장되어 있는 값과 입력받은 값을 비교해서 더 작은 값을 넣을 수 있음
		//배열을 만들면 0으로 초기화 되기 때문에 INF로 초기화 하지 않는다면 0만 계속 들어가게 됨(입력받은 값이 0보다 더 크기 때문)
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				//자기 자신으로의 경로 비용은 0임
				if (i == j) {
					map[i][j] = 0;
				} else {
					//아직 비용을 모르는 상태는 INF
					map[i][j] = INF;	
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			//출발 도시와 도착 도시가 같은데 비용이 다른 경우가 있음 -> 최소 비용을 저장해줘야 하기 때문에 둘중에서 작은 값을 넣어줌
			map[a][b] = Math.min(map[a][b], c);
		}
		
		floyd(n, map);

	}
	
	//플로이드 워샬
	public static void floyd(int n, int[][] map) {		
		//각각 모든 경로에 대해 최단경로를 만들어줌
		for (int k = 1; k <= n; k++) { //k 거쳐가는 도시
			for (int i = 1; i <= n; i++) { //i 시작 도시
				for (int j = 1; j <= n; j++) { //j는 도착 도시
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		//결과 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				//경로가 없는 경우에는 0을 출력해야하기 때문에 0을 넣어줌
				if (map[i][j] == INF) {
					map[i][j] = 0;
				}
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
