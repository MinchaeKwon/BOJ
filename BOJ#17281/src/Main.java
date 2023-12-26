/**
 * 17281 ⚾
 * https://www.acmicpc.net/problem/17281
 * 
 * @author minchae
 * @date 2023. 12. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	static int[][] players; // 각 이닝에서의 타자의 행동
	static int[] order; // 타자 순서
	static boolean[] selected; // 타자가 선택되었는지 확인
	
	static int answer; // 아인타팀이 얻을 수 있는 최대 점수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		players = new int[N][10];
		order = new int[10];
		selected = new boolean[10];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order[4] = 1; // 4번 타자는 1번 선수로 정해져 있음
		selected[4] = true;
		
		permulation(2); // 1번 선수는 이미 순서가 정해져 있으므로 2번 선수부터 시작

		System.out.println(answer);
	}
	
	// 타자의 순서를 정함
	private static void permulation(int depth) {
		if (depth == 10) {
			int score = play();
			answer = Math.max(answer, score);
			
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (!selected[i]) {
				selected[i] = true;
				order[i] = depth;
				
				permulation(depth + 1);
				
				selected[i] = false;
			}
		}
	}
	
	// 게임 시작
	private static int play() {
		int sum = 0;
		
		int idx = 1; // 타자 인덱스 -> 1번 타자부터 시작
		
		for (int i = 0; i < N; i++) {
			int out = 0; // 현재 이닝에서의 아웃 개수
			int score = 0; // 현재 이닝에서 얻는 점수
			boolean[] base = new boolean[4]; // 각 베이스에 선수가 있는지 확인
			
			// 3아웃이 발생하기 전까지 이닝 진행
			while (out < 3) {
				switch (players[i][order[idx]]) { // idx번 타자의 행동
				case 0: // 아웃
					out++;
					break;
				case 1: // 안타
					if (base[3]) {
						score++;
						base[3] = false;
					}
					
					if (base[2]) {
						base[3] = true;
						base[2] = false;
					}
					
					if (base[1]) {
						base[2] = true;
					}
					
					base[1] = true;
					
					break;
				case 2: // 2루타
					if (base[3]) {
						score++;
						base[3] = false;
					}
					
					if (base[2]) {
						score++;
					}
					
					if (base[1]) {
						base[3] = true;
						base[1] = false;
					}
					
					base[2] = true;
					
					break;
				case 3: // 3루타
					if (base[3]) {
						score++;
					}
					
					if (base[2]) {
						score++;
						base[2] = false;
					}
					
					if (base[1]) {
						score++;
						base[1] = false;
					}
					
					base[3] = true;
					
					break;
				case 4: // 홈런
					if (base[3]) {
						score++;
						base[3] = false;
					}
					
					if (base[2]) {
						score++;
						base[2] = false;
					}
					
					if (base[1]) {
						score++;
						base[1] = false;
					}
					
					score++;
					
					break;
				}
				
				idx++; // 다음 타자로 넘어감
				
				// 9번 타자까지 순서가 끝난 경우 1번 타자부터 다시 시작하기 위해 1을 넣어줌
				if (idx == 10) {
					idx = 1;
				}
			}
			
			sum += score; // 해당 이닝에서 얻은 점수 더함
		}
		
		return sum;
	}

}
