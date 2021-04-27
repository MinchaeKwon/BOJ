/**
 * 1475 방 번호
 * https://www.acmicpc.net/problem/1475
 * 
 * @author minchae
 * @date 2021. 4. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int setCount = 1;
		
		// 9를 6으로 취급 -> 배열 크기는 9
		int[] plasticNum = new int[9];
		buy(plasticNum);
		
		// 한 자리씩 나눠서 플라스틱 숫자가 있는지 없는지 확인
		while (N > 0) {
			int num = N % 10;
			
			// 9를 6으로 봄
			if (num == 9) {
				num = 6;
			}
			
			// 남은 숫자가 없을 경우
			if (plasticNum[num] == 0) {
				buy(plasticNum);
				plasticNum[num]--;
				setCount++;
			}
			else {
				plasticNum[num]--;
			}
			
			N /= 10;
		}
		
		System.out.println(setCount);

	}
	
	public static void buy(int[] num) {
		for (int i = 0; i < num.length; i++) {
			// 9를 6으로 취급하기 때문에 6일때 2만큼 증가시킴
			if (i == 6) {
				num[i] += 2;
			} else {
				num[i]++;	
			}
		}
	}

}
