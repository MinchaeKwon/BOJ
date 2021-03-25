/**
 * 9466 텀 프로젝트
 * https://www.acmicpc.net/problem/9466
 * 
 * @author minchae
 * @date 2021.3.20
 * 
 * 사이클이 만들어졌는지 확인하는 배열도 추가해야함 -> 이미 사이클이 만들어졌는데 또 방문하면 안됨
 * 
 * ex) 배열이 없는 경우 
 * 1 -> 3 -> 3
 * 2 -> 1 -> 3 -> 3
 * 3 -> 3
 * 이렇게 사이클이 생성되면 팀이 이상하게 구성이 됨
 * -> 3번 학생 혼자 팀이 되어야하는데 1번, 2번도 팀이 되는 경우가 생기므로 배열을 추가해서 사이클이 됐는지 확인해야함 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] stu;
	static boolean[] visited;
	static boolean[] cycle;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			
			stu = new int[n + 1];
			visited = new boolean[n + 1];
			cycle = new boolean[n + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				stu[j] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			for (int j = 1; j <= n; j++) {
				if (!visited[j]) {
					dfs(j);
				}
			}
			
			System.out.println(n - result);
			
		}

	}
	
	public static void dfs(int n) {
		visited[n] = true;
		
		if (!visited[stu[n]]) { // stu[n]은 n이 같이 팀을 이루고 싶은 학생의 번호 
			dfs(stu[n]); // 다시 탐색 
		}
		else if (!cycle[stu[n]]) { // 다음 노드가 이미 방문된 상태고 사이클이 생성되지 않은 경우에 팀을 이뤄야함 
			result++; // 팀원 한명 추가(n번 학생 추가)
			
			int i = stu[n]; // 현재 팀원과 연결된 팀원들을 추가해줌 
			while (i != n) {
				result++;
				
				i = stu[i];
			}
		}
		
		// 팀을 이뤘으니 true로 바꿔줌 -> 다른 노드에서 n을 방문해도 사이클을 생성 못하게 함 
		cycle[n] = true;
		
	}

}
