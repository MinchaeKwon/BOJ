/**
 * 11931 수 정렬하기 4
 * https://www.acmicpc.net/problem/11931
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for (int v : arr) {
			sb.append(v + "\n");
		}
		
		System.out.println(sb);

	}

}
