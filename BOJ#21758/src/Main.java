/**
 * 21758 꿀 따기
 * https://www.acmicpc.net/problem/21758
 * 
 * @author minchae
 * @date 2024. 8. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 벌1 맨 왼쪽 고정, 벌통 맨 오른쪽 고정 -> 벌2의 위치 이동
// 2. 벌1 맨 오른쪽 고정, 벌통 맨 왼쪽 고정 -> 벌2의 위치 이동
// 3. 벌 1, 2 각각 왼쪽, 오른쪽에 고정 -> 벌통이 움직임
// 세 가지의 경우를 나누어서 계산하고 최댓값을 구함
public class Main {

	static int N;
	static int[] arr;

	static long[] leftSum; // 왼쪽에서 시작한 누적 합
	static long[] rightSum; // 오른쪽에서 시작한 누적 합
	static long total; // 모든 꿀의 양

	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		leftSum = new long[N];
		rightSum = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			leftSum[i] = i > 0 ? leftSum[i - 1] + arr[i] : arr[i];
		}

		for (int i = N - 1; i >= 0; i--) {
			rightSum[i] = i < N - 1 ? rightSum[i + 1] + arr[i] : arr[i];
		}
		
		total = leftSum[N - 1];

		left();
		right();
		both();

		System.out.println(answer);
	}

	// 벌1 맨 왼쪽 고정, 벌통 맨 오른쪽 고정
	private static void left() {
		long bee1;
		long bee2;
		
		// 벌2가 벌1을 제외한 맨 오른쪽에서 (N-2)칸까지 한 칸씩 이동
		for (int i = 1; i <= N - 2; i++) {
			bee1 = total - arr[0] - arr[i]; // 총합 - 벌1 위치에 있는 꿀 - 벌2의 위치에 있는 꿀
			bee2 = total - leftSum[i]; // 총합 - (i + 1) ~ (N - 2)칸까지의 누적합 뺌
			
			if (bee1 + bee2 == 42) {
				System.out.println(bee1 + " " + bee2);
				System.out.println("arr[0] " + arr[0] + ", arr[i]" + arr[i] + ", leftSum[i] " + leftSum[i]);
			}
			
			answer = Math.max(answer, bee1 + bee2);
		}
	}

	// 벌1 맨 오른쪽 고정, 벌통 맨 왼쪽 고정
	private static void right() {
		long bee1;
		long bee2;
		
		// 벌2가 벌통을 제외한 맨 왼쪽에서 1번 칸까지 한 칸씩 이동
		for (int i = N - 2; i >= 1; i--) {
			bee1 = total - arr[N - 1] - arr[i]; // 총합 - 벌1 위치에 있는 꿀 - 벌2의 위치에 있는 꿀
			bee2 = total - rightSum[i]; // 총합 - (i - 1) ~ 1번 위치 칸까지의 누적합 뺌
			
			answer = Math.max(answer, bee1 + bee2);
		}
	}

	// 벌1을 왼쪽에 고정, 벌2를 오른쪽에 고정, 벌통을 이동시킴
	private static void both() {
		long bee1;
		long bee2;
		
		for (int i = 1; i <= N - 2; i++) {
			bee1 = leftSum[i] - arr[0]; // // 벌1 위치에 있는 꿀의 양 빼기
			bee2 = rightSum[i] - arr[N - 1]; // 벌2 위치에 있는 꿀의 양 빼기
			
			answer = Math.max(answer, bee1 + bee2);
		}
	}

}
