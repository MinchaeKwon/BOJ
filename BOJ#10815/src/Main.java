//숫자카드 -> 1920(수 찾기)번과 똑같음
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] A = new int[n];
		
		for(int i = 0; i < A.length; i++)
			A[i] = sc.nextInt();
		Arrays.sort(A);
		
		int m = sc.nextInt();
		int[] B = new int[m];
		
		for(int i = 0; i < B.length; i++) {
			B[i] = sc.nextInt();
			
			if(search(B[i], A) != -1)
				System.out.println("1");
			else
				System.out.println("0");
		}
		
		sc.close();
	}

	private static int search(int key, int[] arr) { //이분 탐색
		int mid = 0;
		int high = arr.length - 1;
		int low = 0;
		
		while (low <= high) { // 아직 숫자들이 남아 있으면
			mid = (low + high) / 2;
			
			if (key == arr[mid])
				return mid; // 탐색 성공
			else if (key > arr[mid])
				low = mid + 1; // 왼쪽 부분리스트 탐색
			else
				high = mid - 1; // 오른쪽 부분리스트 탐색
		}
		
		return -1; // 탐색 실패
	}
}

