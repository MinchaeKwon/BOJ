/**
 * 2164 카드 2
 * https://www.acmicpc.net/problem/2164
 * 
 * @author minchae
 * @date 2024. 2. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		while (q.size() > 1) {
			q.poll(); // 맨 위에 있는 수를 버림
			
			q.add(q.poll()); // 첫 번째 있는 카드를 맨 밑으로 옮김
		}
		
		System.out.println(q.poll());
	}

}
