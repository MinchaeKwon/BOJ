/**
 * 16953 A -> B
 * https://www.acmicpc.net/problem/16953
 * 
 * @author minchae
 * @date 2022. 4. 9.
 * 
 * BFS 이용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	long num;
	int cnt;
	
	public Node(long num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

public class Main2 {
	
	static long A, B;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		System.out.println(bfs());
	}
	
	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(A, 1));
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			if (node.num == B) {
				return node.cnt;
			}
			
			if (node.num * 2 <= B) {
				q.add(new Node(node.num * 2, node.cnt + 1));
			}
			
			if (node.num * 10 + 1 <= B) {
				q.add(new Node(node.num * 10 + 1, node.cnt + 1));
			}
		}
		
		return -1;
	}

}
