package Practic1.Date;

import java.util.Scanner;

public class InputDate {

	int day, month, year;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out
				.println("Input Year (The Year must be between 1970 and 2099)");
		int year = in.nextInt();
		System.out.println("Input Month (The Month must be between 1 and 12)");
		int month = in.nextInt();
		System.out.println("Input Day (The Day must be between 1 and 31");
		int day = in.nextInt();

		System.out.println(new Date(day, month, year));

		in.close();

	}
}
