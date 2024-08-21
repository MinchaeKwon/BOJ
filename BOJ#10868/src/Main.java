/**
 * 10868 최솟값
 * https://www.acmicpc.net/problem/10868
 * 
 * @author minchae
 * @date 2024. 8. 21.
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static long[] num;
	static long[] tree; // 구간의 최소값 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken());
		
		num = new long[N + 1];
		tree = new long[4 * N];
		
		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, N); // 세그먼트 트리 초기화
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 항상 최상단 노드부터 봄
			sb.append(query(1, 1, N, a, b)).append("\n");
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
		
		return tree[node] = Math.min(val1, val2);
	}
	
	private static long query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return Long.MAX_VALUE;
		}
		
		if (start >= left && end <= right) {
			return tree[node];
		}
	
		int mid = (start + end) / 2;
		
		// 왼쪽, 오른쪽 자식 노드를 루트로 함
		long val1 = query(2 * node, start, mid, left, right);
		long val2 = query(2 * node + 1, mid + 1, end, left, right);

		return Math.min(val1, val2);
	}

}
