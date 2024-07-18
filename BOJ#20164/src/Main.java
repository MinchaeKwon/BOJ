/**
 * 20164 홀수 홀릭 호석
 * https://www.acmicpc.net/problem/20164
 * 
 * @author minchae
 * @date 2024. 7. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int min = Integer.MAX_VALUE;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int num = Integer.parseInt(br.readLine());
		
		divide(num, getOddCnt(num)); // 초기 숫자 홀수 개수를 같이 보냄
		
		System.out.println(min + " " + max);
	}

	private static void divide(int num, int cnt) {
		if (num < 10) { // 수가 한 자리인 경우
			// 홀수 개수 최소, 최대 갱신
			min = Integer.min(min, cnt);
			max = Integer.max(max, cnt);
			
			return;
		} else if (num < 100) { // 수가 두 자리인 경우
			int sum = (num / 10) + (num % 10); // 2개로 나눠서 합을 구해 새로운 수 생성
			divide(sum, cnt + getOddCnt(sum));
		} else { // 수가 세 자리 이상인 경우
			String s = String.valueOf(num);
			
			// 임의의 위치에서 끊기 : 3개로 나누기 위해 -2한 길이부터 시작
			for (int i = 0; i < s.length() - 2; i++) {
				for (int j = i + 1; j < s.length() - 1; j++) {
					int n1 = Integer.parseInt(s.substring(0, i + 1));
					int n2 = Integer.parseInt(s.substring(i + 1, j + 1));
					int n3 = Integer.parseInt(s.substring(j + 1, s.length()));
					
					// 3개로 나눈 수를 더해 새로운 수 생성
					int sum = n1 + n2 + n3;
					divide(sum, cnt + getOddCnt(sum));
				}
			}
		}
	}
	
	// 홀수 개수 구하기
	private static int getOddCnt(int num) {
		int cnt = 0;
		
		while (num > 0) {
			int result = num % 10;
			
			if (result % 2 == 1) {
				cnt++;
			}
			
			num /= 10;
		}
		
		return cnt;
	}
	
}
