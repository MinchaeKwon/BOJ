/**
 * 15235 Olympiad Pizza
 * https://www.acmicpc.net/problem/15235
 * 
 * @author minchae
 * 2021. 9. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Person {
	int order;
	int eat;
	
	public Person(int order, int eat) {
		this.order = order;
		this.eat = eat;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] pizza = new int[N];
		Queue<Person> q = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
			
			q.add(new Person(i, 0));
		}
		
		int cnt = 0; // 먹은 피자의 개수
		int[] result = new int[N];
		
		while (!q.isEmpty()) {
			Person person = q.poll();
			
			int num = person.order;
			int eat = person.eat;
			
			eat++; // 해당 차례의 사람이 피자를 먹었기 때문에 증가
			cnt++; // 먹은 피자의 개수 증가
			
			if (pizza[num] == eat) {
				result[num] = cnt;
			} else {
				q.add(new Person(num, eat));
			}
		}
		
		for (int answer : result) {
			System.out.print(answer + " ");
		}

	}

}
