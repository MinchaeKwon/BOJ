/**
 * 20055 컨베이어 벨트 위의 로봇
 * https://www.acmicpc.net/problem/20055
 * 
 * @author minchae
 * @date 2024. 2. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055 {
	
	static int N, K;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N * 2];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N * 2; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		simulation();
	}
	
	private static void simulation() {
		int step = 0;
		
		boolean[] robot = new boolean[N];
		
		while (!isFinish()) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			int last = A[N * 2 - 1];
			
			for (int i = N * 2 - 1; i > 0; i--) {
				A[i] = A[i - 1];
			}
			
			A[0] = last;
			
			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			
			robot[0] = false;
			robot[N - 1] = false;
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			for (int i = N - 1; i > 0; i--) {
				if (robot[i - 1] && !robot[i] && A[i] > 0) {
					robot[i - 1] = false;
					robot[i] = true;
					A[i]--;
				}
			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (A[0] > 0) {
				robot[0] = true;
				A[0]--;
			}
			
			step++;
		}
		
		System.out.println(step);
	}
	
	private static boolean isFinish() {
		int cnt = 0;
		
		for (int i = 0; i < N * 2; i++) {
			if (A[i] == 0) {
				cnt++;
			}
		}
		
		if (cnt >= K) {
			return true;
		}
		
		return false;
	}

}
