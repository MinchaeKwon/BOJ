/**
 * 컨베이어 벨트 위의 로봇
 * https://www.acmicpc.net/problem/20055
 * 
 * @author minchae
 * @date 2023. 4. 3.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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
		
		System.out.println(getStepCnt());
	}
	
	public static int getStepCnt() {
		int step = 0;
		
		boolean[] robot = new boolean[N];
		
		while(isProcess()) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 움직임
			
			// 벨트 움직이기
			int last = A[A.length - 1]; // 마지막 벨트에 있는 내구도를 첫 번째로 옮겨야 하기 때문에 저장해둠
			for (int i = N * 2 - 1; i > 0; i--) {
				A[i] = A[i - 1];
			}
			A[0] = last;
			
			// 로봇도 같이 움직이기
			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			
			robot[N - 1] = false; // 로봇이 내려가는 위치는 false
			
			// 2. 로봇 한 칸 앞으로 이동시키기 (i부터 증가하는 for문은 데이터가 섞여서 답이 제대로 나오지 않음)
			for (int i = robot.length - 1; i > 0; i--) {
				// 현재 칸에 로봇이 있으면서 이동하려는 칸에 로봇이 없고 내구도가 0이 아닌 경우
				if (robot[i - 1] && !robot[i] && A[i] > 0) {
					robot[i] = true;
					robot[i - 1] = false;
					A[i]--;
				}
			}
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇을 올리고 내구도를 1 감소시킴
			if (A[0] > 0) {
				robot[0] = true;
				A[0]--;
			}
			
			step++; // 단계 증가
		}
		
		return step;
	}
	
	public static boolean isProcess() {
		int cnt = 0;
		for (int i = 0; i < N * 2; i++) {
			if (A[i] == 0) {
				cnt++;
			}
		}
		
		// 내구도가 0인 벨트의 개수가 K개 이상인 경우
		if (cnt >= K) {
			return false;
		}
		
		return true;
	}

}
