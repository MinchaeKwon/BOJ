//소수&팰린드롬

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		while(true) {
			if(isPrime(n) && isPalindrom(n)) {
				System.out.println(n);
				sc.close();
				return;
			}
			n++;
		}

	}
	
	public static boolean isPrime(int n) {
		if(n == 1)
			return false;
		
		for(int i = 2; i * i <= n; i++)
			if(n % i == 0)
				return false;
		return true;
	}
	
	public static boolean isPalindrom(int n) {
		int rn = reverseNumber(n);
		
		if(n == rn)
			return true;
		else
			return false;
	}
	
	public static int reverseNumber(int n)
	{
		int result = 0;

		while (n != 0)
		{
			result = (result * 10) + (n % 10);
			n /= 10;
		}

		return result;
	}

}
