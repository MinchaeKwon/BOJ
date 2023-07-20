/**
 * 3079 입국심사
 * https://www.acmicpc.net/problem/3079
 * 
 * @author minchae
 * @date 2023. 6. 20. 수정
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long M;
	static int[] times;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 심사대 개수
		M = Integer.parseInt(st.nextToken()); // 사람 수
		
       		 times = new int[N];

		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getTotalTime());
	}
	
	public static long getTotalTime() {
	        Arrays.sort(times); // 오름차순 정렬
	
	        long low = 0;
	        long high = times[N - 1] * M; // 맨 뒤에 있는 값에 인원 수를 곱한 값이 가장 많이 걸리는 시간임
	
	        while (low <= high) {
	            long mid = (low + high) / 2;
	
	            long people = 0; // mid 시간동안 심사한 인원 -> 처리할 수 있는 사람의 수
	
	            for (long time : times) {
	                long cnt = mid / time; // 한 심사대에서 맡을 수 있는 사람의 수
	
	                // 처리할 수 있는 수가 M을 넘는 경우 더이상 진행하지 않고 for문 탈출
	                if (people >= M) {
	                    break;
	                }
	
	                people += cnt;
	            }
	
	            if (people < M) {
	                low = mid + 1; // M명보다 적을 경우 더 많은 시간이 소요된다는 의미이므로 mid + 1을 해줌
	            } else {
	                high = mid - 1; // mid 시간동안 심사한 인원이 M명 이상일 경우 high = mid - 1을 통해 탐색 범위를 줄여줌
	            }
	        }
	
	        return low;
	}

}
