/**
 * 13335 트럭
 * https://www.acmicpc.net/problem/13335
 * 
 * @author minchae
 * @date 2023. 12. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, w, L;
	static int[] weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수
		w = Integer.parseInt(st.nextToken()); // 다리의 길이
		L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중
		
		weight = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		Queue<Integer> q = new LinkedList<>();
		
		int answer = 0;
		int sum = 0; // 다리 위의 트럭 무게
		int idx = 0;
		
		// 모든 트럭을 다리에 다 올릴 때까지 반복
		while (idx < weight.length) {
			// 큐의 크기가 다리 길이랑 같은 경우(트럭이 다리를 다 건넜다는 의미)
			// 다리를 건넜기 때문에 다리 위 트럭 무게에서 해당 트럭 무게를 빼주고 큐에서도 빼줌
			if (q.size() == w) {
				sum -= q.poll();
			} else { 
				// 큐가 다 차지 않은 상태(다리 위에 트럭이 올라갈 수 있는 경우)일 경우 트럭이 다리 위에 올라갈 수 있는지 확인
				// (현재 다리 위에 있는 트럭 무게 + 다리에 올라가야 하는 트럭 무게) > 다리 최대 하중
				if (sum + weight[idx] > L) {
					// 이미 다리 위에 올라가있는 트럭이 다리를 건널 수 있도록 큐에 0을 추가하고 시간을 증가시킴
					q.add(0);
					answer++;
				} else {
					// L보다 작은 경우 다리에 트럭이 올라갈 수 있으므로 다리에 트럭을 올림 -> 큐에 트럭을 추가
					q.add(weight[idx]);
					sum += weight[idx];
					answer++;

					idx++; // 다리에 트럭이 올라갈 경우 인덱스 증가
				}
			}
		}
		
		// 마지막 트럭이 올라가고 나서 반복문이 끝남 -> 해당 트럭이 다리를 지나가야 하므로 다리 길이를 더해줌
        System.out.println(answer + w);
	}

}
