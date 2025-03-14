/**
 * 11505 구간 곱 구하기
 * https://www.acmicpc.net/problem/11505
 *
 * @author minchae
 * @date 2025. 3. 14.
 **/

import java.io.*;
import java.util.*;

public class Main {
	
	static final int MOD = 1_000_000_007;
	
	static int N, M, K;
	
	static long[] num;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		num = new long[N + 1];
		tree = new long[N * 4];

		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Integer.parseInt(st.nextToken());
			
			if (a == 1) {
				update(1, 1, N, b, c);
			} else {
				sb.append(query(1, 1, N, b, (int) c)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = num[start];
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		long val1 = init(left, start, mid);
		long val2 = init(right, mid + 1, end);
		
		return tree[node] = (val1 * val2) % MOD;
	}
	
	private static long query(int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return 1;
		}
		
		if (start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		long val1 = query(2 * node, start, mid, left, right);
		long val2 = query(2 * node + 1, mid + 1, end, left, right);
		
		return (val1 * val2) % MOD;
	}
	
	private static long update(int node, int start, int end, int idx, long val) {
		if (idx < start || idx > end) {
			return tree[node];
		}
		
		if (start == end) {
			return tree[node] = val;
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		return tree[node] = (update(left, start, mid, idx, val) * update(right, mid + 1, end, idx, val)) % MOD;
	}
}
