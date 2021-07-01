/**
 * 1865 웜홀
 * https://www.acmicpc.net/problem/1865
 * 
 * @author minchae
 * @date 2021. 7. 1.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int end;
	int weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Main {
	
	static final int INF = 1000000000;
	static ArrayList<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 지점의 개수
			int M = Integer.parseInt(st.nextToken()); // 도로의 개수
			int W = Integer.parseInt(st.nextToken()); // 웜홀의 개수
			
			nodeList = new ArrayList[N + 1];
			
			for (int j = 1; j < N + 1; j++) {
				nodeList[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < M + W; j++) {
				st = new StringTokenizer(br.readLine());
				
				int S = Integer.parseInt(st.nextToken()); // 시작 지점
				int E = Integer.parseInt(st.nextToken()); // 도착 지점
				int time = Integer.parseInt(st.nextToken()); // 줄어드는 시간
				
				if (j < M) {
					// 도로는 방향이 없기 때문에 둘 다 추가해줌
					nodeList[S].add(new Node(E, time));
					nodeList[E].add(new Node(S, time));
				}
				else {
					// 웜홀은 방향이 있지만 웜홀을 지나치는 경우 시간이 거꾸로 가기 때문에 음수로 바꾸어서 추가해줌
					nodeList[S].add(new Node(E, -time));	
				}
			}
			
			// 음수 사이클이 발생한 경우 YES, 아닌 경우 NO를 출력
			System.out.println(bellman(N) ? "YES" : "NO");
		}
		
	}
	
	public static boolean bellman(int n) {
		boolean isUpdate = false;
		int[] dist = new int[n + 1];
		
		Arrays.fill(dist, INF);
		dist[1] = 0; // 시작 지점은 0으로 초기화
		
		// 모든 지점을 N - 1번 순회
		for (int i = 1; i < n; i++) {
			isUpdate = false;
			
			// 모든 간선을 순회하는 for문
			// 음수 사이클이 있는 경우 N번째에서도 최단 거리를 찾아내기 때문에 for문을 N번까지 돌려보고 업데이트 되는지 확인해야 한다.
			// 만약 N번째에서 사이클이 발생할 경우에는 isUpdate이 다시 false로 변경되지 않기 때문에 true일 경우에는 사이클이 발생한 것
			for (int j = 1; j <= n; j++) {
				for (Node node : nodeList[j]) {
					if (dist[j] != INF && dist[node.end] > dist[j] + node.weight) {
						dist[node.end] = dist[j] + node.weight;
						isUpdate = true;
					}
				}
			}
			
			// 진행 중에 최단 거리가 하나라도 갱신되지 않을 경우 반복문 종료
			if (!isUpdate) {
				break;
			}
		}
		
		// 사이클이 발생한 경우 true 반환
		if (isUpdate) {
            for (int i = 1; i <= n; i++) {
                for (Node node : nodeList[i]) {
                    if (dist[i] != INF && dist[node.end] > dist[i] + node.weight) {
                        return true;
                    }
                }
            }
        }
 
        return false;
	}

}
