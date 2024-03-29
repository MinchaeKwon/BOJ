/**
 * 1038 감소하는 수
 * https://www.acmicpc.net/problem/1038
 * 
 * @author minchae
 * @date 2024. 3. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int N;
	static ArrayList<Long> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		if (N < 10) {
			System.out.println(N);
		} else if (N >= 1023) { // 0 ~ 9까지의 숫자로 10자리를 만들 수 있는 경우는 2^10 = 1024가지, 아무것도 선택하지 않는 1가지 제외하면 1023
			System.out.println(-1);
		} else {
			for (int i = 0; i < 10; i++) {
				getNum(i, 1);
			}
			
			Collections.sort(list);
			System.out.println(list.get(N));
		}
	}
	
	private static void getNum(long num, int idx) {
		if (idx > 10) {
			return;
		}
		
		list.add(num);
		
		for (int i = 0; i < num % 10; i++) {
			getNum((num * 10) + i, idx + 1);
		}
	}

}
