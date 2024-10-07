/**
 * 12846 무서운 아르바이트
 * https://www.acmicpc.net/problem/1284
 * 
 * @author minchae
 * @date 2024. 10. 6.
 * */

import java.util.*;
import java.io.*;

public class Main {

	static int N;

	static int[] num;
	static int[] tree; // 구간의 최소값을 가지는 인덱스 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 수의 개수

		num = new int[N + 1];
		tree = new int[4 * N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		init(1, 1, N); // 세그먼트 트리 초기화
		
		System.out.println(getMax(1, N));
	}

	// 세그먼트 트리 초기화
	private static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;

		// 왼쪽, 오른쪽 자식을 기준으로 재귀 호출
		init(left, start, mid);
		init(right, mid + 1, end);
		
		if (num[tree[left]] <= num[tree[right]]) {
			tree[node] = tree[left];
		} else {
			tree[node] = tree[right];
		}
	}

	private static int query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return -1;
		}

		if (start >= left && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;

		// 왼쪽, 오른쪽 자식 노드를 루트로 함
		int val1 = query(2 * node, start, mid, left, right);
		int val2 = query(2 * node + 1, mid + 1, end, left, right);
		
		if (val1 == -1) {
			return val2;
		} else if (val2 == -1) {
			return val1;
		}
		
		return num[val1] <= num[val2] ? val1 : val2;
	}

	// 분할 정복 -> 최대 이익을 가지는 구간 찾기
	private static long getMax(int left, int right) {
		int m = query(1, 1, N, left, right);

		long result = (long) (right - left + 1) * num[m];
		
		// right 범위 줄이기
		if (left <= m - 1) {
			result = Math.max(result, getMax(left, m - 1));
		}

		// left 범위 높이기
		if (m + 1 <= right) {
			result = Math.max(result, getMax(m + 1, right));

		}
		
		return result;
	}

}
