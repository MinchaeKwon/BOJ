/**
 * 2251 물통
 * https://www.acmicpc.net/problem/2251
 * 
 * @author minchae
 * @date 2022. 3. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bucket {
	int a;
	int b;
	int c;
	
	public Bucket(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class Main {

	static ArrayList<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		bfs(a, b, c);
		
		Collections.sort(result);
		for (int num : result) {
			System.out.print(num + " ");
		}
	}
	
	private static void bfs(int maxA, int maxB, int maxC) {
		Queue<Bucket> q = new LinkedList<>();
		boolean[][][] visited = new boolean[maxA + 1][maxB + 1][maxC + 1];
		
		q.add(new Bucket(0, 0, maxC)); // 초기 상태
		
		while (!q.isEmpty()) {
			Bucket bucket = q.poll();
			
			if (!visited[bucket.a][bucket.b][bucket.c]) {
				if (bucket.a == 0) {
					result.add(bucket.c);
				}
				
				visited[bucket.a][bucket.b][bucket.c] = true;
				
				// B -> A
				if (bucket.a + bucket.b <= maxA) {
					q.add(new Bucket(bucket.a + bucket.b, 0, bucket.c));
				} else {
					q.add(new Bucket(maxA, bucket.a + bucket.b - maxA, bucket.c));
				}
				
				// C -> A
				if (bucket.a + bucket.c <= maxA) {
					q.add(new Bucket(bucket.a + bucket.c, bucket.b, 0));
				} else {
					q.add(new Bucket(maxA, bucket.b, bucket.a + bucket.c - maxA));
				}
				
				// A -> B
				if (bucket.a + bucket.b <= maxB) {
					q.add(new Bucket(0, bucket.a + bucket.b, bucket.c));
				} else {
					q.add(new Bucket(bucket.a + bucket.b - maxB, maxB, bucket.c));
				}
				
				// C -> B
				if (bucket.b + bucket.c <= maxB) {
					q.add(new Bucket(bucket.a, bucket.b + bucket.c, 0));
				} else {
					q.add(new Bucket(bucket.a, maxB, bucket.b + bucket.c - maxB));
				}
				
				// A -> C
				if (bucket.a + bucket.c <= maxC) {
					q.add(new Bucket(0, bucket.b, bucket.a + bucket.c));
				} else {
					q.add(new Bucket(bucket.a + bucket.c - maxC, bucket.b, maxC));
				}
				
				// B -> C
				if (bucket.b + bucket.c <= maxC) {
					q.add(new Bucket(bucket.a, 0, bucket.b + bucket.c));
				} else {
					q.add(new Bucket(bucket.a, bucket.b + bucket.c - maxC, maxC));
				}
			}
		}
	}

}
