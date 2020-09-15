//보물

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Integer[] A = new Integer[n];
		Integer[] B = new Integer[n];
		
		//배열 A, B 입력 받기
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0)
					A[j] = sc.nextInt();
				else
					B[j] = sc.nextInt();
			}
		}
		
		Arrays.sort(A);
		Arrays.sort(B, Comparator.reverseOrder()); //Collections.reverseOrder() 써도 됨
		/* B배열은 재배열 하지 말라고 했지만 재배열 안된 B에 A의 숫자를 끼워맞춰서 넣은 것과
		   A와 B를 정렬해서 더하는 것은 덧셈의 교환법칙에 의하면 결국 같은 결과이다. */
		
		int result = 0;
		for(int i = 0; i < n; i++)
			result += A[i] * B[i]; //각 배열의 큰 값과 작은 값을 곱해서 더하면 최소값이 됨
		
		System.out.println(result);
		
		sc.close();
	}

}
