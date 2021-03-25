/**
 * 2668 숫자고르기
 * https://www.acmicpc.net/problem/2668
 * 
 * @author minchae
 * @date 2021.3.26
 * 
 * 9466 텀 프로젝트와 비슷한 문제 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int[] arr;
	static boolean[] visited;
	static boolean[] finished;
	
	static ArrayList<Integer> numList = new ArrayList<>();
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1];
		visited = new boolean[n + 1];
		finished = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		result = 0;
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		
		System.out.println(result); // 뽑힌 정수 개수 출력
		
		Collections.sort(numList); // 오름차순으로 정렬 
		
		// 뽑힌 정수 출력 
		for (int num : numList) {
			System.out.println(num);
		}

	}
	
	public static void dfs(int n) {
		visited[n] = true;
		
		// arr[n]은 다음에 탐색할 정수 -> n과 연결된 정수 
		if (!visited[arr[n]]) {
			dfs(arr[n]); // 다시 탐색 
		}
		else if (!finished[arr[n]]) { // 사이클이 생성되지 않은 경우 
			result++; // 뽑힌 정수 개수 증가 
			numList.add(arr[n]);
			
			int i = arr[n];
			while (i != n) {
				result++;
				numList.add(arr[i]);
				
				i = arr[i];
			}
		}
		
		// 다른 노드에서 n을 방문해도 사이클을 생성 못하게 true로 바꿔줌 
		finished[n] = true;
		
	}

}
