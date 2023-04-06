/**
 * 이차원 배열과 연산
 * https://www.acmicpc.net/problem/17140
 * 
 * @author minchae
 * @date 2023. 4. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Number implements Comparable<Number> {
	int n;
	int cnt;
	
	public Number(int n, int cnt) {
		this.n = n;
		this.cnt = cnt;
	}
	
	@Override
	public int compareTo(Number o) {
		// 등장한 횟수가 같으면 번호를 기준으로 오름차순
		if (this.cnt == o.cnt) {
			return this.n - o.n;
		}
		
		return this.cnt - o.cnt; // 등장한 횟수를 기준으로 오름차순
	}
}

public class Main {

	static int r, c, k;
	static int[][] map = new int[3][3];
	
	static int[][] calMap; // 행이나 열에 대해서 연산을 수행한 값을 저장
	static int[] cntArr; // 수가 몇 번 등장하는지 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while (true) {
			if (r <= map.length && c <= map[0].length && map[r - 1][c - 1] == k) {
				System.out.println(time);
				break;
			}
			
			if (time > 100) {
				System.out.println(-1);
				break;
			}
			
			int rLen = map.length; // 행의 길이
			int cLen = map[0].length; // 열의 길이
			
			if (rLen >= cLen) {
				// R연산
				calculateR();
			} else {
				// C연산
				calculateC();
			}
			
			time++;
		}

	}
	
	// 모든 행에 대해서 연산 수행
	private static void calculateR() {
		calMap = new int[101][101];
		int maxLen = 0;
		
		for (int i = 0; i < map.length; i++) {
			cntArr = new int[101];
			
			for (int j = 0; j < map[i].length; j++) {
				cntArr[map[i][j]]++; // 각 번호의 등장 횟수 저장
			}
			
			// 번호와 번호의 등장 횟수 저장 -> 0은 무시해야 하기 때문에 1부터 시작
			PriorityQueue<Number> pq = new PriorityQueue<>();
			for (int k = 1; k < cntArr.length; k++) {
				if (cntArr[k] > 0) {
					pq.add(new Number(k, cntArr[k]));
				}
			}
			
			maxLen = Math.max(maxLen, pq.size());
			
			// 우선순위 큐를 사용했기 때문에 정렬된 상태 -> calMap에 연산한 값을 저장
			int idx = 0;
			while (!pq.isEmpty()) {
				Number cur = pq.poll();
				
				calMap[i][idx] = cur.n;
				idx++;
				
				calMap[i][idx] = cur.cnt;
				idx++;
			}
		}
		
		int nr = map.length;
		int nc = 2 * maxLen;
		
		// 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버림
		if (nr > 100) {
			nr = 100;
		}
		
		if (nc > 100) {
			nc = 100;
		}
		
		map = new int[nr][nc];
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < nc; j++) {
				map[i][j] = calMap[i][j]; // 원본 배열에 값 넣어줌
			}
		}
		
	}
	
	// 모든 열에 대해서 연산 수행
	private static void calculateC() {
		calMap = new int[101][101];
		int maxLen = 0;
		
		for (int j = 0; j < map[0].length; j++) {
			cntArr = new int[101];
			
			for (int i = 0; i < map.length; i++) {
				cntArr[map[i][j]]++; // 각 번호의 등장 횟수 저장
			}
			
			// 번호와 번호의 등장 횟수 저장 -> 0은 무시해야 하기 때문에 1부터 시작
			PriorityQueue<Number> pq = new PriorityQueue<>();
			for (int k = 1; k < cntArr.length; k++) {
				if (cntArr[k] > 0) {
					pq.add(new Number(k, cntArr[k]));
				}
			}
			
			maxLen = Math.max(maxLen, pq.size());
			
			// 우선순위 큐를 사용했기 때문에 정렬된 상태 -> calMap에 연산한 값을 저장
			int idx = 0;
			while (!pq.isEmpty()) {
				Number cur = pq.poll();
				
				calMap[idx][j] = cur.n;
				idx++;
				
				calMap[idx][j] = cur.cnt;
				idx++;
			}
		}
		
		int nr = 2 * maxLen;
		int nc = map[0].length;
		
		// 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버림
		if (nr > 100) {
			nr = 100;
		}
		
		if (nc > 100) {
			nc = 100;
		}
		
		map = new int[nr][nc];
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < nc; j++) {
				map[i][j] = calMap[i][j]; // 원본 배열에 값 넣어줌
			}
		}
	}

}
