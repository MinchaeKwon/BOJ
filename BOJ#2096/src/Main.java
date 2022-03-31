/**
 * 2096 내려가기
 * https://www.acmicpc.net/problem/2096
 * 
 * @author minchae
 * @date 2022. 3. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] maxDp = new int[3];
		int[] minDp = new int[3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			
			if (i == 0) {
				maxDp[0] = minDp[0] = n1;
				maxDp[1] = minDp[1] = n2;
				maxDp[2] = minDp[2] = n3;
			} else {
				// 변경된 값을 더하지 않기 위해 기존 값을 저장해 두는 것
				int beforeMax0 = maxDp[0];
				int beforeMax2 = maxDp[2];
				
				maxDp[0] = n1 + Math.max(maxDp[0], maxDp[1]);
				maxDp[2] = n3 + Math.max(maxDp[1], maxDp[2]);
				maxDp[1] = n2 + Math.max(beforeMax0, Math.max(maxDp[1], beforeMax2));
				
				int beforeMin0 = minDp[0];
				int beforeMin2 = minDp[2];
				
				minDp[0] = n1 + Math.min(minDp[0], minDp[1]);
				minDp[2] = n3 + Math.min(minDp[1], minDp[2]);
				minDp[1] = n2 + Math.min(beforeMin0, Math.min(minDp[1], beforeMin2));
			}
		}
		
		int max = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
		int min = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
		
		System.out.println(max + " " + min);
	}

}
