/**
 * 16953 A -> B
 * https://www.acmicpc.net/problem/16953
 * 
 * @author minchae
 * @date 2022. 4. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int cnt = 1;
		
		// B에서 A로 만들기
		while (A != B) {
			if (B < A) {
				cnt = -1;
				break;
			}
	
			if (B % 2 == 0) {
				// 2로 나누어 떨어지는 경우 2로 나눔 (A -> B : 2를 곱하는 경우)
				B /= 2;
			} else if (B % 10 == 1) {
				// 10으로 나누었을 때 나머지가 1이면 10으로 나눔 (A -> B : 1을 더하는 경우)
				B /= 10;
			} else {
				cnt = -1;
				break;
			}
			
			cnt++;
		}
		
		System.out.println(cnt);
	}

}
