/**
 * N과 M 시리즈
 * https://www.acmicpc.net/problem/15649
 * 
 * @author minchae
 * @date 2023. 10. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class NM {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;
	
	static int[] arr;
	static boolean[] visited;
	static int[] result;
	static LinkedHashSet<String> output = new LinkedHashSet<>();

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		type1(); // 숫자 입력 X
//		type2(); // 숫자 입력 O
		type3(); // 숫자 입력 O (중복 입력 가능성 있음)
	}
	
	// 숫자 입력 받지 않음
	private static void type1() {
		arr = new int[M];
		visited = new boolean[N];
		
		NM1(0);
//		NM2(0, 0);
//		NM3(0);
//		NM4(0, 0);
	}
	
	// 배열 입력 받음
	private static void type2() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		result = new int[M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
//		NM5(0);
//		NM6(0, 0);
//		NM7(0);
		NM8(0, 0);
	}
	
	// 배열 입력 받음, 입력되는 수가 중복일 수 있음 (중복되면 안됨)
	private static void type3() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		result = new int[M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
//		NM9(0);
//		NM10(0, 0);
//		NM11(0);
		NM12(0, 0);
		
		output.forEach(System.out::println);
	}
	
	// BOJ 15469 N과 M (1) - 순열
	private static void NM1(int depth) {
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				arr[depth] = i + 1;
				visited[i] = true;
				
				NM1(depth + 1);
				
				visited[i] = false;
			}
		}
	}
	
	// BOJ 15650 N과 M (2) - 순열 (오름차순)
	// 오름차순으로 출력해야 하기 때문에 start 변수 추가 -> start 변수로 다음 수부터 탐색하게 되므로 visited 배열 쓸 필요 없음
	private static void NM2(int depth, int start) {
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			arr[depth] = i + 1;
			NM2(depth + 1, i + 1);
		}
	}
	
	// BOJ 15651 N과 M (3) - 조합
	private static void NM3(int depth) {
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[depth] = i + 1;
			NM3(depth + 1);
		}
	}
	
	// BOJ 15652 N과 M (4) - 조합 (오름차순)
	private static void NM4(int depth, int start) {
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			arr[depth] = i + 1;
			NM4(depth + 1, i); // 중복이 허용되기 때문에 i에 +1을 안해도됨
		}
	}
	
	// BOJ 15654 N과 M (5) - 순열
	private static void NM5(int depth) {
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				result[depth] = arr[i];
				visited[i] = true;
				
				NM5(depth + 1);
				
				visited[i] = false;
			}
		}
	}
	
	// BOJ 15655 N과 M (6) - 순열 (오름차순)
	private static void NM6(int depth, int start) {
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			NM6(depth + 1, i + 1); // 중복을 허용하지 않기 때문에 i에 1을 더해줌
		}
	}
	
	// BOJ 15656 N과 M (7) - 조합
	private static void NM7(int depth) {
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			result[depth] = arr[i];
			NM7(depth + 1);
		}
	}
	
	// BOJ 15657 N과 M (8) - 조합 (오름차순)
	private static void NM8(int depth, int start) {
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			NM8(depth + 1, i);
		}
	}
	
	// BOJ 15663 N과 M (9) - 조합
	private static void NM9(int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : result) {
				sb.append(num + " ");
			}
			
			output.add(sb.toString());
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				result[depth] = arr[i];
				visited[i] = true;
				
				NM9(depth + 1);
				
				visited[i] = false;
			}
		}
	}
	
	// BOJ 15664 N과 M (10) - 순열 (오름차순)
	private static void NM10(int depth, int start) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : result) {
				sb.append(num + " ");
			}
			
			output.add(sb.toString());
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			NM10(depth + 1, i + 1); // 중복을 허용하지 않기 때문에 i에 1을 더해줌
		}
	}
	
	// BOJ 15665 N과 M (11) - 조합
	private static void NM11(int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : result) {
				sb.append(num + " ");
			}
			
			output.add(sb.toString());
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			result[depth] = arr[i];
			NM11(depth + 1);
		}
	}
	
	// BOJ 15666 N과 M (12) - 조합 (오름차순)
	private static void NM12(int depth, int start) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int num : result) {
				sb.append(num + " ");
			}
			
			output.add(sb.toString());
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			NM12(depth + 1, i);
		}
	}

}
