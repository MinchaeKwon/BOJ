/**
 * 18428 감시 피하기
 * https://www.acmicpc.net/problem/18428
 * 
 * @author minchae
 * @date 2023. 12. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	static char[][] map; // 선생님이 존재하는 칸은 T, 학생이 존재하는 칸은 S, 장애물이 존재하는 칸은 O
	
	static ArrayList<Pair> teacher = new ArrayList<>(); // 선생님 위치 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				
				if (map[i][j] == 'T') {
					teacher.add(new Pair(i, j));
				}
			}
		}
		
		backtracking(0);

		System.out.println("NO");
	}
	
	// 빈 칸에 장애물 3개를 설치
	private static void backtracking(int depth) {
		if (depth == 3) {
			// 감시를 피할 수 있는 경우 종료
			if (isAvoid()) {
				System.out.println("YES");
				System.exit(0);
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'X') {
					map[i][j] = 'O';
					backtracking(depth + 1);
					map[i][j] = 'X'; // 원복
				}
			}
		}
	}
	
	// 모든 학생들이 감시에서 벗어날 수 있는지 확인
	private static boolean isAvoid() {
		for (Pair cur : teacher) {
			for (int i = 0; i < 4; i++) {
				int nx = cur.x;
				int ny = cur.y;
				
				// 특정 방향으로 쭉 가면서 장애물, 학생이 있는지 확인
				while (true) {
					nx += dx[i];
					ny += dy[i];
					
					// 범위를 벗어나거나 장애물을 만난 경우 더이상 감시를 못하기 때문에 break로 반복문 빠져나가고 다음 방향 탐색
					if (!isRange(nx, ny) || map[nx][ny] == 'O') {
						break;
					}
					
					// 학생을 만나면 감시를 피할 수 없는 것이기 때문에 false 반환
					if (map[nx][ny] == 'S') {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
