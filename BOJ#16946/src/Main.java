
/**
 * 16946 벽 부수고 이동하기 4
 * https://www.acmicpc.net/problem/16946
 * 
 * @author minchae
 * @date 2023. 8. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static HashMap<Integer, Integer> emptyMap = new HashMap<>(); // 빈칸 그룹 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';

				// 빈칸 그룹을 묶어서 번호를 1부터 매기기 위해 벽은 -1로 변경
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}

		int idx = 1; // 그룹 번호

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 빈칸을 발견할 경우 그룹번호 매김
				if (map[i][j] == 0) {
					emptyMap.put(idx, checkEmpty(i, j, idx));
					idx++;
				}
			}
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					sb.append(getMoveCount(i, j)); // 벽이 있는 경우 자신을 포함한 주변의 빈칸 개수를 더함
				} else {
					sb.append(0); // 빈칸일 경우 0 출력
				}
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	// 연속된 빈칸 그룹을 구하고 그룹번호를 매김
	private static int checkEmpty(int x, int y, int num) {
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { x, y });
		map[x][y] = num;

		int cnt = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				// 맵에 빈칸 그룹 번호를 매기기 때문에 따로 visited 배열은 사용 X, 0(빈칸)인 경우 아직 방문하지 않은 것으로 간주
				if (checkRange(nx, ny) && map[nx][ny] == 0) {
					q.add(new int[] { nx, ny });
					map[nx][ny] = num;

					cnt++;
				}
			}
		}

		return cnt;
	}

	// 벽을 부수고 이동할 수 있는 곳의 개수를 셈 -> 벽을 발견하면 그 주변의 빈칸 그룹을 구해 각 그룹의 개수를 더함
	private static int getMoveCount(int x, int y) {
		int sum = 1; // 벽의 개수도 포함시키기 때문에 1부터 시작
		HashSet<Integer> check = new HashSet<>(); // 그룹 개수를 중복으로 더하는 것을 방지

		// 상하좌우 확인하면서 빈칸인 경우 해당 그룹의 개수를 더함
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (checkRange(nx, ny) && map[nx][ny] != -1) {
				check.add(map[nx][ny]);
			}
		}

		for (int num : check) {
			sum += emptyMap.get(num);
		}

		return sum % 10;
	}

	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}