/**
 * 1107 리모컨
 * https://www.acmicpc.net/problem/1107
 * 
 * @author minchae
 * @date 2021. 4. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] broken;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		broken = new boolean[10]; // 채널이 10개만 있으니까 크기를 10으로 함
		
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		int result = Math.abs(N - 100);
		
		for (int i = 0; i < 1000000; i++) {
			int cnt = isPossible(i); // i까지 숫자 버튼을 눌러서 이동 가능한지 확인하고 숫자 버튼을 누른 횟수를 셈
			
			// cnt가 0이라면 버튼이 고장나서 이동하지 못하는 경우이고, 1이상이라면 +,- 버튼을 눌러서 N까지 이동해야함
			if (cnt > 0) {
				int press = Math.abs(i - N); // press: 숫자만 눌러서 이동할 수 있는 채널에서 N까지 +,- 버튼을 누르는 횟수
				
				result = Math.min(result, cnt + press); // 버튼을 최소로 누르는 횟수
			}
		}
		
		System.out.println(result);

	}
	
	public static int isPossible(int btn) {
		int cnt = 0;
		
		// 0버튼은 고장났을 경우 누르지 않거나, 한 번만 누르는 경우(숫자 0은 한번만 클릭하면 끝이기 때문)밖에 없음
		if (btn == 0) {
			return broken[0] ? 0 : 1;
		}
		
		while (btn > 0) {
			// 1의 자리보다 큰 숫자가 올수도 있기 때문에 (btn % 10)로 나머지를 구해줌
			// 만약 해당 번호가 고장난 버튼일 경우 0을 반환
			if (broken[btn % 10]) {
				return 0;
			}
			
			// 고장난 버튼이 아닌 경우 숫자 버튼을 누른 횟수를 증가시키고 다음 자리 숫자를 버튼으로 누를 수 있는지 확인하기 위해 (btn / 10)을 해줌
			cnt++;
			btn /= 10;
		}
		
		return cnt;
		
	}

}
