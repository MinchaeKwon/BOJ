/**
 * 마법사 상어와 블리자드
 * https://www.acmicpc.net/problem/21611
 * 
 * @author minchae
 * @date 2023. 4. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 하 우 상 좌
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int N, M;
	static int[][] input;

	static int[] map; // 입력받은 input 2차원 배열을 1차원 배열에 저장 -> 달팽이처럼 되어있기 때문에 1차원으로 사용 가능
	static int[] ball;
	static int[][] bzIdx;
	static int[] bzConvert = {0, 3, 1, 0, 2}; // 입력값 d를 알맞게 변환 ex) d=1 -> bzConvert[1]=3 -> bzIdx[3] 참조

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N][N];
		map = new int[N * N];
		ball = new int[4];
		bzIdx = new int[4][N / 2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		makeBzIdx();
		makeMap();

		// 블리자드 M번 시전
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리

			blizzard(bzConvert[d], s); // 블리자드 마법
			move(); // 구슬 옮기기
			
			while (bomb()) {} // 더 이상 폭발하는 구슬이 없을때까지 반복
			
			change(); // 구슬 변화
		}

		// 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)를 출력
		System.out.println(1 * ball[1] + 2 * ball[2] + 3 * ball[3]);
	}

	// 1차원 map에서 블리자드 쏠 수 있게 인덱스 생성 (좌하우상)
	static void makeBzIdx() {
		int cnt = 1;
		int level = 1;

		for (int j = 0; j < (N / 2); j++) {
			for (int i = 0; i < 4; i++) {
				bzIdx[i][j] = cnt;

				if (i == 3) {
					cnt += 2 * level + 1;
				} else {
					cnt += level * 2;
				}
			}

			level++;
		}
	}

	// 2차원 배열을 달팽이 모양으로 탐색하면서 1차원 배열에 값 저장
	private static void makeMap() {
		// 상어 위치부터 시작
		int r = N / 2;
		int c = N / 2;

		int idx = 1; // 상어 위치 제외하고 1부터 시작

		for (int level = 1; level <= N / 2; level++) {
			r -= 1;
			c -= 1;

			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < level * 2; i++) {
					r += dx[d];
					c += dy[d];

					map[idx] = input[r][c];
					idx++;
				}
			}
		}
	}

	// bzdIdx를 참고하여 s만큼 0으로 만들기
	private static void blizzard(int d, int s) {
		for (int i = 0; i < s; i++) {
			map[bzIdx[d][i]] = 0;
		}
	}

	// 구슬 왼쪽으로 옮기기
	private static void move() {
		Queue<Integer> q = new LinkedList<Integer>();

		// map 탐색하여 0 아닌 값들 큐에 저장
		for (int i = 1; i < map.length; i++)
			if (map[i] != 0) {
				q.offer(map[i]);
			}

		// map 초기화 후 큐에서 뽑아 1번부터 채우기
		Arrays.fill(map, 0);
		for (int i = 1; i < map.length; i++) {
			if (!q.isEmpty()) {
				map[i] = q.poll();
			}
		}
	}

	// 연속된 구슬 폭발
	private static boolean bomb() {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		int cnt = 1;
		boolean flag = false; // 한번이라도 터뜨렸는지 flag

		for (int i = 1; i < map.length; i++) {
			// 연속되지 않는 구슬 발견하면 종료
			if (map[i] == 0) {
				break;
			}

			// 2번째 구슬부터 앞 구슬과 비교
			if (i != 1) {
				if (map[i - 1] == map[i]) { // 앞의 구슬과 번호가 같은 경우
					cnt++;
				} else {
					// 다른 경우 cnt가 4이상일 때만 cnt만큼 덱에서 뽑아가며 구슬 터뜨리기
					if (cnt >= 4) {
						flag = true;

						for (int c = 0; c < cnt; c++) {
							ball[dq.pollLast()]++;
						}
					}
					
					cnt = 1;
				}
			}
			
			// 현재 구슬번호 덱에 삽입
			dq.offerLast(map[i]);
		}

		// 끝에 남은 연속된 구슬 처리
		if (cnt >= 4) {
			flag = true;
			for (int c = 0; c < cnt; c++) {
				ball[dq.pollLast()]++;
			}
		}

		// map 초기화 후 덱 앞에서부터 뽑아 1부터 저장
		Arrays.fill(map, 0);
		for (int i = 1; i < map.length; i++) {
			if (!dq.isEmpty()) {
				map[i] = dq.pollFirst();
			}
		}
		
		return flag; // 터뜨림 여부 반환
	}

	// 구슬 변화
	static void change() {
		// 모든 구슬 0이면 종료
		if (map[1] == 0) {
			return;	
		}

		Queue<Integer> q = new ArrayDeque<Integer>();
		int cnt = 1;
		int i;

		// 2번 구슬부터 탐색
		for (i = 2; i < map.length; i++) {
			if (map[i] == 0) {
				break;
			}

			if (map[i - 1] == map[i]) {
				// 앞 구슬과 같은 경우
				cnt++;
			} else {
				// 다르면 카운트와 앞 구슬 번호를 큐에 삽입
				q.offer(cnt);
				q.offer(map[i - 1]);
				cnt = 1;
			}
		}

		// 마지막 구슬 처리
		q.offer(cnt);
		q.offer(map[i - 1]);

		// map 초기화 후 큐에서 뽑아 map에 1부터 저장
		Arrays.fill(map, 0);
		for (i = 1; i < map.length; i++) {
			if (!q.isEmpty()) {
				map[i] = q.poll();
			}
		}
	}

}
