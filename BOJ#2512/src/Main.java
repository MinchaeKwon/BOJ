import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2512 예산
 * https://www.acmicpc.net/problem/2512
 * 
 * @author minchae
 * @date 2022. 4. 26.
 */



public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] budget = new int[N];
		int end = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, budget[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int start = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
		}
	}

}
