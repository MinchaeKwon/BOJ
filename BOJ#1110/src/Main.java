//더하기 사이클
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int cnt = 0; // 사이클 길이
		int one = n / 10; //첫번째 자리
		int two = n % 10; //두번째 자리
		int newN = 0;

		do {
			cnt++;

			int sum = one + two;
			int twoTemp = two;

			two = sum % 10; //10보다 클수도 있기 때문에 나머지 연산
			one = twoTemp % 10; //10보다 클수도 있기 때문에 나머지 연산
			newN = one * 10 + two;
		} while (newN != n);

//		while(true) {
//			cnt++;
//			
//			int sum = one + two;
//			int twoTemp = two;
//			two = sum % 10;
//			one = twoTemp % 10;
//			int newN = one * 10 + two;
//			
//			if(newN == n)
//				break;
//		}

		System.out.println(cnt);

		sc.close();

	}

}
