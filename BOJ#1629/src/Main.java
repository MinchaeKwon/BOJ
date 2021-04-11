/**
 * 1629 곱셈
 * https://www.acmicpc.net/problem/1629
 * 
 * @author minchae
 * @date 2021. 4. 12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		System.out.println(pow(A, B, C));

	}
	
	public static long pow(long a, long b, long c) {
		if(b == 1) {
			return a % c;
		}
		
		long tmp = pow(a, b / 2, c);
		
		if (b % 2 == 0) {
			return (tmp * tmp) % c;
		} else {
			return ((tmp * tmp % c) * a) % c;
		}
		
	}

}
