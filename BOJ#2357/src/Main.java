/**
 * 2357 최솟값과 최댓값
 * https://www.acmicpc.net/problem/2357
 *
 * @author minchae
 * @date 2025. 3. 15.
 **/

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	
	static long[] num;
	static long[] minTree;
	static long[] maxTree;
	
	static long min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new long[N + 1];
		minTree = new long[N * 4];
		maxTree = new long[N * 4];
		
		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		
		init(maxTree, 0, 1, 1, N);
		init(minTree, 1, 1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			max = -1;
			min = Long.MAX_VALUE;
			
			query(maxTree, 0, 1, 1, N, a, b);
			query(minTree, 1, 1, 1, N, a, b);
			
			sb.append(min + " " + max).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	private static void init(long[] tree, int type, int node, int start, int end) {
		if (start == end) {
			tree[node] = num[start];
			return;
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		init(tree, type, left, start, mid);
		init(tree, type, right, mid + 1, end);
		
		if (type == 0) {
			tree[node] = Math.max(tree[left], tree[right]);
		} else {
			tree[node] = Math.min(tree[left], tree[right]);
		}
	}
	
	private static void query(long[] tree, int type, int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return;
		}
		
		if (start >= left && end <= right) {
			if (type == 0) {
				max = Math.max(max, tree[node]);
			} else {
				min = Math.min(min, tree[node]);
			}
			
			return;
		}
		
		int mid = (start + end) / 2;
		query(tree, type, 2 * node, start, mid, left, right);
		query(tree, type, 2 * node + 1, mid + 1, end, left, right);
	}

}
