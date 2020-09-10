//팰린드롬수

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n;

		do {
			n = sc.nextInt();

			if (n != 0) {
				if (n == reverseNumber(n))
					System.out.println("yes");
				else
					System.out.println("no");
			}

		} while (n != 0);

		sc.close();
	}

	public static int reverseNumber(int n) {
		int result = 0;

		while (n != 0) {
			result = (result * 10) + (n % 10);
			n /= 10;
		}

		return result;
	}

}
