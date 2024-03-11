/**
 * 2310 어드벤처 게임
 * https://www.acmicpc.net/problem/2310
 * 
 * @author minchae
 * @date 2024. 3. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	
	static char[] room;
	static int[] fee;
	static ArrayList<Integer>[] list;
	
	static boolean isFinish;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			n = Integer.parseInt(br.readLine());
			
			if (n == 0) {
				break;
			}
			
			room = new char[n + 1];
			fee = new int[n + 1];
			list = new ArrayList[n + 1];
			
			isFinish = false;
			visited = new boolean[n + 1];
			
			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				room[i] = st.nextToken().charAt(0);
				fee[i] = Integer.parseInt(st.nextToken());
				
				list[i] = new ArrayList<>();
				
				while (true) {
					int next = Integer.parseInt(st.nextToken());
					
					if (next == 0) {
						break;
					}
					
					list[i].add(next);
				}
			}
			
			dfs(1, 0);
			
			sb.append(isFinish ? "Yes" : "No");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int num, int money) {
		// 이미 n번 방에 도착한 경우 종료
		if (isFinish) {
			return;
		}
		
		if (num == n) {
			isFinish = true;
			return;
		}
		
		for (int next : list[num]) {
			if (visited[next]) {
				continue;
			}
			
			if (room[next] == 'L') {
				if (money < fee[next]) {
					money = fee[next];
				}
			} else if (room[next] == 'T') {
				if (money >= fee[next]) {
					money -= fee[next];
				} else {
					return; // 금액을 지불할 수 없는 경우 종료
				}
			}
			
			visited[next] = true;
			dfs(next, money);
			visited[next] = false;
		}
	}

}
