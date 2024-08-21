/**
 * 14427 수열과 쿼리 15
 * https://www.acmicpc.net/problem/14427
 * 
 * @author minchae
 * @date 2024. 8. 21.
 */

import java.util.*;
import java.io.*;

// 최소값을 가지는 인덱스를 출력하는 것이기 때문에 인덱스, 값을 가지는 객체를 추가해서 트리 구성
public class Main {
	
	static class Node {
		int idx;
		long num;
		
		public Node(int idx, long num) {
			this.idx = idx;
			this.num = num;
		}
	}
	
	static long[] A;
	static Node[] tree; // 구간의 최소값 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 수열 크기
		
		A = new long[N + 1];
		tree = new Node[4 * N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine()); // 쿼리 개수
		
		init(1, 1, N); // 세그먼트 트리 초기화
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int idx = Integer.parseInt(st.nextToken());
				long v = Integer.parseInt(st.nextToken()); // 바꿀 숫자
				
				A[idx] = v;
				update(1, 1, N, idx, v);
			} else {
				sb.append(tree[1].idx).append("\n"); // 가장 최상단 노드에 최소값이 저장되어 있음
			}
		}
		
		System.out.println(sb.toString());
	}
	
	// 세그먼트 트리 초기화
	private static Node init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = new Node(start, A[start]);
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		// 왼쪽, 오른쪽 자식을 기준으로 재귀 호출
		Node val1 = init(left, start, mid);
		Node val2 = init(right, mid + 1, end);
		
		return tree[node] = val1.num > val2.num ? val2 : val1;
	}
	
	// 특정 인덱스의 값 변경
	private static Node update(int node, int start, int end, int idx, long val) {
		// 노드가 가지는 값의 구간과 배열의 인덱스 값이 포함되지 않는 경우
		if (idx < start || end < idx) {
			return tree[node];
		}
		
		if (start == end) {
			tree[node].num = val;
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		int left = 2 * node;
		int right = 2 * node + 1;
		
		// 리프 노드까지 계속 자식 노드를 탐색함
		Node val1 = update(left, start, mid, idx, val);
		Node val2 = update(right, mid + 1, end, idx, val);
		
		return tree[node] = val1.num > val2.num ? val2 : val1;
	}
	
}
