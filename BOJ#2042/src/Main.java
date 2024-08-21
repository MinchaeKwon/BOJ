/**
 * 2043 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 * 
 * @author minchae
 * @date 2024. 8. 21.
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static long[] num;
	static long[] tree; // 구간 합을 저장할 트리 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간 합 구하는 횟수
		
		num = new long[N + 1];
		tree = new long[4 * N];
		
		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, N); // 세그먼트 트리 초기화
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				update2(1, 1, N, b, c);
				
				// 변경할 값과 기존 값의 차이로 구간합 변경
//				long diff = c - num[b];
//				num[b] = c;
//				
//				update(1, 1, N, b, diff);
			} else {
				// 항상 최상단 노드부터 봄
				sb.append(query(1, 1, N, b, (int) c)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	// 세그먼트 트리 초기화
	private static long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = num[start];
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		// 왼쪽, 오른쪽 자식을 기준으로 재귀 호출
		long val1 = init(left, start, mid);
		long val2 = init(right, mid + 1, end);
		
		return tree[node] = val1 + val2;
	}
	
	// 구간 합 구하기
	// 노드가 담당하는 구간 (현재 구간) : [start, end]
	// 구해야 하는 범위 : [left, right]
	private static long query(int node, int start, int end, int left, int right) {
		// 1. 노드 현재 구간이 구하려고 하는 합의 구간에 속하지 않은 경우
		if (end < left || right < start) {
			return 0; // 범위가 겹치지 않으면 0 반환 (지정된 범위 밖에 있는 경우)
		}
		
		// 노드 구간이 구하려고 하는 합의 구간에 속하는 경우
		if (start >= left && end <= right) {
			return tree[node]; // 현재 노드가 지정된 범위 내에 있는 경우 노드 값 반환
		}
		
		// 노드가 지정된 범위 일부에 있는 경우
		// 1. 구하려고 하는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우 (겹쳐져 있는 경우)
		// 2. 구하려고 하는 합의 구간을 모두 포함하는 경우
		
		int mid = (start + end) / 2;
		
		// 왼쪽, 오른쪽 자식 노드를 루트로 함
		long val1 = query(2 * node, start, mid, left, right);
		long val2 = query(2 * node + 1, mid + 1, end, left, right);

		return val1 + val2; // 결과 더해서 반환
	}
	
	// 특정 인덱스의 값 변경 -> 원래 값과 변경할 값의 차이를 이용
	private static void update(int node, int start, int end, int idx, long diff) {
		// 노드가 가지는 값의 구간과 배열의 인덱스 값이 포함되지 않는 경우
		if (idx < start || end < idx) {
			return;
		}
		
		// 노드가 가지는 값의 구간과 배열의 인덱스(값이 변경 될 인덱스)가 포함되는 경우
		// 노드 값에 차이를 더함
		tree[node] += diff;
		
		// 리프 노드가 아닌 경우
		if (start != end) {
			int mid = (start + end) / 2;
			int left = 2 * node;
			int right = 2 * node + 1;
			
			// 리프 노드까지 계속 자식 노드를 탐색함
			update(left, start, mid, idx, diff);
			update(right, mid + 1, end, idx, diff);
		}
	}
	
	private static long update2(int node, int start, int end, int idx, long val) {
		// 노드가 가지는 값의 구간과 배열의 인덱스 값이 포함되지 않는 경우
		if (idx < start || end < idx) {
			return tree[node];
		}
		
		if (start == end) {
			return tree[node] = val;
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		// 리프 노드까지 계속 자식 노드를 탐색함
		return tree[node] = update2(left, start, mid, idx, val) + update2(right, mid + 1, end, idx, val);
	}

}
