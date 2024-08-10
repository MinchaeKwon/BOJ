/**
 * 2632 피자판매
 * https://www.acmicpc.net/problem/2632
 * 
 * @author minchae
 * @date 2024. 8. 11.
 */

import java.io.*;
import java.util.*;

public class Main {

	static int size, m, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		int[] a = new int[m];
		int[] b = new int[n];
		
		int aMax = 0;
		int bMax = 0;
		
		for (int i = 0; i < m; i++) {
			a[i] = Integer.parseInt(br.readLine());
			aMax += a[i];
		}
		
		int[] aCount = new int[Math.max(aMax, size) + 1];
		
		// 피자 조각을 선택하지 않았을 때와, 모두 선택한 경우의 수는 1임
		aCount[0] = 1;
		aCount[aMax] = 1;
		
		comb(a, aCount);
		
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(br.readLine());
			bMax += b[i];
		}
		
		int[] bCount = new int[Math.max(bMax, size) + 1];
		
		bCount[0] = 1;
		bCount[bMax] = 1;
		
		comb(b, bCount);
		
		int answer = 0;
		
		// size가 되는 경우의 수를 구함
		for (int i = 0; i <= size; i++) {
			answer += aCount[i] * bCount[size - i];
		}
		
		System.out.println(answer);
	}
	
	// 특정 피자 배열에서 만들 수 있는 사이즈의 조합 구함 -> 만들 수 있는 사이즈의 개수 세기
	private static void comb(int[] pizza, int[] cnt) {
		// i : 기준이 되는 피자 인덱스
		for (int i = 0; i < pizza.length; i++) {
			int sum = 0;
			
			// 자기 자신부터 시작해서 length - 1까지 확인
			for (int j = 0; j < pizza.length - 1; j++) {
				int tmp = pizza[(i + j) % pizza.length]; // 인덱스 범위를 초과할 수 있기 때문에 % 사용
				
				// 피자 사이즈를 초과하는 경우는 더이상 볼 필요 없음
				if (sum + tmp > size) {
					break;
				}
				
				sum += tmp;
				cnt[sum]++;
			}
		}
	}

}
