/**
 * 12866 돌 그룹
 * https://www.acmicpc.net/problem/12886
 * 
 * @author minchae
 * @date 2021. 7. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Stone {
	int a;
	int b;
	int c;
	
	public Stone(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(A, B, C) ? 1 : 0);

	}
	
	public static boolean bfs(int a, int b, int c) {
		// a, b, c의 합이 3으로 나눠지지 않는 경우에는 돌의 개수를 같게 만들 수 없기 때문에 false 반환
		if ((a + b + c) % 3 != 0) {
			return false;
		}
		
		Queue<Stone> q = new LinkedList<>();
		
		// 돌 그룹 2개의 개수만 알면 나머지는 자동으로 개수가 정해지기 때문에 2차원 배열을 사용
		boolean[][] visited = new boolean[1501][1501];
		
		q.add(new Stone(a, b, c));
		visited[a][b] = true;
		
		while (!q.isEmpty()) {
			Stone s = q.poll();
			
			a = s.a;
			b = s.b;
			c = s.c;
			
			if (a == b && b == c) {
				return true;
			}
			
			// 크기가 같지 않은 두 그룹을 고름 -> (A, B), (B, C), (A, C) 세 가지 경우가 있음
			// 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y로 하고, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만듦
			if (a != b) {
				int na = a > b ? a - b : a * 2;
				int nb = a > b ? b * 2 : b - a;
				
				if (!visited[na][nb]) {
					q.add(new Stone(na, nb, c));
					visited[na][nb] = true;
				}
			}
			
			if (a != c) {
				int na = a > c ? a - c : a * 2;
				int nc = a > c ? c * 2 : c - a;
				
				if (!visited[na][nc]) {
					q.add(new Stone(na, b, nc));
					visited[na][nc] = true;
				}
			}
			
			if (b != c) {
				int nb = b > c ? b - c : b * 2;
				int nc = b > c ? c * 2 : c - b;
				
				if (!visited[nb][nc]) {
					q.add(new Stone(a, nb, nc));
					visited[nb][nc] = true;
				}
			}
			
		}
		
		return false;
	}

}
