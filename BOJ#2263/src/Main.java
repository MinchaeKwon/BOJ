/**
 * 2263 트리의 순회
 * https://www.acmicpc.net/problem/2263
 * 
 * @author minchae
 * @date 2021. 4. 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] inorder;
	static int[] postorder;
	static int[] idx_inorder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		inorder = new int[n + 1]; // 중위순회
		postorder = new int[n + 1]; // 후위순회
		idx_inorder = new int[n + 1]; // 중위순회 노드들의 인덱스 저장 -> 루트노드 위치를 알아내기 위해 사용
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			idx_inorder[inorder[i]] = i;
		}
		
		// 후위순회의 맨 끝 노드가 전위순회의 루트노드
		// 중위순회에서 후위순회의 맨 끝 노드를 기준으로 반을 나누면 왼쪽 부분이 트리의 왼쪽 자식, 오른쪽 부분이 트리의 오른쪽 자식노드
		preorder(1, n, 1, n);

	}
	
	public static void preorder(int in_start, int in_end, int post_start, int post_end) {
		if (in_start > in_end || post_start > post_end) return;
		
		int root = postorder[post_end];
		
		System.out.print(root + " ");
		
		int idx_root = idx_inorder[root]; // 중위순회에서 루트노드의 인덱스
		int left = idx_root - in_start; // 중위순회에서 루트노드를 기준으로 왼쪽 부분에 몇개의 노드가 있는지 알아냄
		
		preorder(in_start, idx_root - 1, post_start, post_start + left - 1); // 왼쪽 자식노드 탐색
		preorder(idx_root + 1, in_end, post_start + left, post_end - 1); //오른쪽 자식노드 탐색
		
	}

}
