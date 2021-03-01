/**
 * 수 정렬하기 3
 * https://www.acmicpc.net/problem/10989
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 계수 정렬(Counting Sort) 사용
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[10001]; //수의 범위가 1 ~ 10000이므로 크기를 10001로 함
		
		for (int i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine())]++; //입력받은 숫자의 빈도 수를 저장함
		}
		
		//숫자는 0이 입력되지 않으므로 i는 1부터 시작
		for (int i = 1; i < 10001; i++) {
			while (arr[i] > 0) { //숫자 i의 개수가 0이 아닐 때까지 출력
				sb.append(i + "\n");
				arr[i]--;
			}
		}
		
		System.out.println(sb);
		
	}

}
