/**
 * 1461 도서관
 * https://www.acmicpc.net/problem/1461
 * 
 * @author minchae
 * @date 2024. 8. 16.
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	
	static int[] books;
	
	static ArrayList<Integer> plus;
	static ArrayList<Integer> minus;
	
	static int max;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		books = new int[N];
		plus = new ArrayList<>();
		minus = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
			
			if (books[i] > 0) {
				plus.add(books[i]);
			} else if (books[i] < 0) {
				minus.add(books[i]);
			}
			
			max = Math.max(max, Math.abs(books[i]));
		}
		
		// 가장 멀리 있는 것부터 시작해야 모든 책을 가져올 수 있음
		Collections.sort(plus, Collections.reverseOrder());
		Collections.sort(minus);
		
		solve();

		System.out.println(answer);
	}
	
	private static void solve() {
		for (int i = 0; i < plus.size(); i++) {
			if (i % M == 0) {
				int value = plus.get(i);
				answer += value == max ? value : value * 2; // 책을 모두 제자리에 놔둔 후에는 다시 0으로 돌아올 필요가 없기 때문에 2배 X
			}
		}
		
		for (int i = 0; i < minus.size(); i++) {
			if (i % M == 0) {
				int value = Math.abs(minus.get(i));
				answer += value == max ? value : value * 2;
			}
		}
	}

}
