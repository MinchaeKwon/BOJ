/**
 * 13265 색칠하기
 * https://www.acmicpc.net/problem/13265
 * 
 * @author minchae
 * @date 2024. 3. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	
	static ArrayList<Integer>[] list;
	static int[] color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n + 1];
			color = new int[n + 1];
			
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				list[x].add(y);
				list[y].add(x);
			}
			
			boolean possible = false;
			
			for (int i = 1; i <= n; i++) {
				if (color[i] == 0) {
					possible = bfs(i);
				}
				
				if (!possible) {
					break;
				}
			}
			
			System.out.println(possible ? "possible" : "impossible");
		}

	}
	
	private static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		color[start] = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int num : list[cur]) {
				if (color[num] == 0) {
					q.add(num);
					color[num] = color[cur] * -1;
				} else if (color[num] == color[cur]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
