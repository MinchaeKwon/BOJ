//연속 최대값 구하기

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = sc.nextInt();
		
		int lastIndex = n - 1;
		int max = max_subArray(arr, 0, lastIndex);
		
		System.out.println(max);
		
		sc.close();

	}
	
	private static int left_max_sum(int[] arr, int left, int right) {
		int sum = arr[right];
		int max = arr[right];
		
		for(int i = right - 1; i >= left; i--) {
			sum += arr[i];
			
			if(max < sum)
				max = sum;
		}
		
		return max;
	}
	
	
	private static int right_max_sum(int[] arr, int left, int right) {
		int sum = arr[left];
		int max = arr[left];
		
		for(int i = left + 1; i <= right; i++) {
			sum += arr[i];
			
			if(max < sum)
				max = sum;
		}
		
		return max;
	}
	
	private static int max_subArray(int[] arr, int left, int right) {
		int mid = (left + right) / 2;
		int maxSum = 0;
		
		if(left == right)
			return arr[left];
		
		int v1 = max_subArray(arr, left, mid); //왼쪽합 재귀, 왼쪽 sub array들 중에서 연속인 최대 누적합
		int v2 = max_subArray(arr, mid + 1, right); //오른쪽합 재귀, 오른쪽 sub array의 연속인 최대 누적합
		int v3 = left_max_sum(arr, left, mid) + right_max_sum(arr, mid + 1, right); // array의 중앙값을 포함하는 sub array의 최대 누적 합
		
		if(v1 > v2)
			maxSum = v1;
		else
			maxSum = v2;
		if(v3 > maxSum)
			maxSum = v3;
		
		return maxSum;
	}

}
