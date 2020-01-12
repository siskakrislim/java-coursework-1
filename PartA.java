
//NAME: SISKA KRISTANTI LIM
//STUDENT NO.: 170281939
import java.util.Random;

public class PartA {

	static String greetings[] = { "hello", "hallo", "bok", "bonjour", "zdravstvuyte", "namaskaar", "hai", "nee how",
			"ne ho" };// see loop12()

	// loop 20 times
	// PROBLEM: Infinite loop
	/*REASON: The loop start with 0, and it is true because it is less than 20, and
	  0 is subtracted by 1 at each iteration (i= 0,-1,-2,-3,...) and this goes to
	  infinity as the i will always be less than 20.
	*/
	/*SOLUTION: To print loop for 20 times, modify the post-decrement into
	 post-increment (change from i-- to i++) in the final expression of the for
	 loop.
	*/
	private static void loop1() {
		for (int i = 0; i < 20; i--) {
			System.out.println("In loop " + i);
		}
		System.out.println("Out of loop");
	}

	// loop 10 times
	// PROBLEM: "In loop" is printed repeatedly and repeatedly, and it leads to
	// unreachable code.
	/*REASON: The for loop has an empty syntax, which means that there is no
	 initialization expression so nothing is executed, no guard means no
	 condition, and no final expression that also executes nothing. The
	 conditional guard is empty that is why it comes up to true, and the body of
	 the loop can be executed. Then, going back to the for loop, seeing that no
	 final expression so nothing is executed. Again, the empty guard leads to true
	 so the body loop is executed once more, and this process is kept active and
	 will not stop.
	*/
	/*SOLUTION: To print the loop 10 times, it is advisable to set up the three
	 important expressions in the for loop. Initialize the int i to 0, then in the
	 condition of i < 10 as well as add i++ in the final expression to increment 1
	 whenever the loop starts again, just like for(int i=0;i<10;i++);
	*/
	private static void loop2() {
		for (;;)
			System.out.println("In loop");
		System.out.println("Out of loop");
	}

	// loop 10 times
	// PROBLEM: Infinite loop and the loop number is always 0.
	/*REASON: This loop lacks another statement where it is because of that
	 inexistence, the loop never ends.
	*/
	/*SOLUTION: Must add the post-increment i++ to increase the loop number in
	 every process until it meets the loop condition. In other words, instead of
	 "In loop 0" every time the loop is running, by having i++ inserted above the
	 statement System.out.println("In loop "+i) and at the same time inside the
	 "do" curly brackets, it becomes In loop 1, In loop 2,..., In loop 10. Then at
	 the last line "Out of loop" will finally be printed. The correct code is: do
	 { i++; System.out.println("In loop "+i); }
	*/
	private static void loop3() {
		int i = 0;
		do {
			System.out.println("In loop " + i);
		} while (i < 10);
		System.out.println("Out of loop");
	}

	// loop 5 times
	// PROBLEM: The loop never ends and the i keeps increasing by 1, hence leads to
	// unreachable code.
	/*REASON: The condition (guard) in the while loop and the initial expression
	 which is int i = 5, always evaluates true since 5 is always less than 10,
	 that is why the loop keeps printing.
	*/
	/*SOLUTION: Let the guard in the while loop be i<10 instead of 5<10 because the
	 i value may vary but the integer 5 always stays as it is.
	*/
	private static void loop4() {
		int i = 5;
		while (5 < 10) {
			System.out.println("In loop i = " + i);
			i++;
		}
		System.out.println("Out of loop");
	}

	// loop 11 times
	// PROBLEM: It directly prints out the "Out of loop", fails to loop 11 times.
	/*REASON: The guard part in the while loop gives a false to the two given
	 integers, m and n since n is greater than m (n=21 and m=10), having "!" in
	 the while loop guard will of course always give a false.
	*/
	/*SOLUTION: Delete "!" in the while loop guard and the loop will print 11
	 times.
	*/
	private static void loop5() {
		int m = 10;
		int n = 21;
		while (!(n > m)) {
			System.out.println("In loop.");
			n--;
		}
		System.out.println("Out of loop.");
	}

	// loop until i is equal to j
	// PROBLEM: The program cannot even compile and run, leading to an unreachable code.
	/*REASON: The while loop guard has a boolean statement false. It makes error to
	 the code because the body loop could not reach the codes outside the loop.
	 Moreover, the false statement makes the codes declared above cannot pass in
	 to the while loop because false statement prohibits that.
	*/
	/*SOLUTION: The false statement in the while loop guard may be replaced by
	 while(i!=j), and it will work.
	*/
	private static void loop6() {
		Random r = new Random();
		int i = 0;
		int j = 10;
		while (false) {
			i = r.nextInt(10);
			j = r.nextInt(1000);
			System.out.println("i = " + i + " and j = " + j);
			if (i == j)
				break;
		}
		System.out.println("out of loop");
	}

	// loop until i is equal to j
	// PROBLEM: Infinite loop
	/*REASON: Although the i is declared as i = r.nextInt(j);, but j has no that
	 kind of method declared, therefore the loop never ends as i will only
	 generate random number ranges from 0 to 9 while j remains 10 always. Another,
	 System.out.println("i = "+i+" and j = "+j); cannot be programmed below
	 if(i==j)break; as it will result to an error when the bound is not positive.
	*/
	/*SOLUTION: Construct j = r.nextInt(11); after i = r.nextInt(j); as well as
	 relocate System.out.println("i = "+i+" and j = "+j); before if(i==j) break;
	 so that the same value of i and j, which is 10, will be printed before the
	 loop breaks.
	 */
	private static void loop7() {
		Random r = new Random();
		int i = 0;
		int j = 10;
		while (true) {
			i = r.nextInt(j);
			if (i == j)
				break;
			System.out.println("i = " + i + " and j = " + j);
		}
		System.out.println("out of loop");
	}

	// loop until we randomly generate a 7
	// PROBLEM: The loop never stops although a number 7 is generated.
	/*REASON: The if-statement only says that if i equals to 7, it is false but it
	 does not mean that the loop will end.
	*/
	/*SOLUTION: Inside the curly brackets in the if-statement, change the stop =
	 false; to break; so that the loop stops when 7 is generated.
	*/
	private static void loop8() {
		boolean stop = false;
		Random r = new Random();
		int i;
		int count = 1;
		while (!stop) {
			i = r.nextInt(100);
			System.out.println("Random number is " + i);
			if (i == 7) {
				stop = false;
			}
			count++;
		}
		System.out.println("Took " + count + " tries");
	}

	// loop 60 times (outer loop 10 times plus inner loop 5 x 10 times)
	// PROBLEM: The inner loop never ends.
	/*REASON: There is a wrong declaration inside the inner loop, where the i++ in
	 the inner loop makes the program cannot run as it is because the variable
	 declared inside the inner while loop is j, not i. Therefore, variable i in
	 the inner loop is always true since there is no limit for variable i in the
	 inner loop itself.
	*/
	/*SOLUTION: Change the incorrect i++ to j++ inside the inner loop.
	*/
	private static void loop9() {
		int i = 0;
		int j = 0;
		while (i < 10) {
			System.out.println("In loop 1 " + i);
			while (j < 5) {
				System.out.println("In loop 2 " + i);
				i++;
			}
			j = 0;
			i++;
		}
		System.out.println("Out of loops");
	}

	// draw a 9 x 9 rectangle using asterisks
	// PROBLEM: It prints out bottom left triangle instead of a rectangle.
	/*REASON: There is a mistake in the inner loop's guard part as to print stars
	 in a row, it cannot exceed the current number for int i. For example, when
	 i=1 and j=0, so for j<i, it is 0<1, therefore an asterisk is printed in row
	 1. When j increments by 1, it is now 1<1, which is false, and the inner loop
	 ends and so the program runs for the next row. However, for 9 x 9 rectangle,
	 it is supposed to have 9 asterisks in every row, instead of only 1.
	*/
	/*SOLUTION: In the j<i inside inner loop, edit it as j<k so that the length and
	 height will be the same. Also, the int k = 10 should be int k = 9 because it
	 wants a 9 x 9 rectangle.
	*/
	private static void loop10() {
		int k = 10;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// Draw a pyramid using asterisks, the pyramid has 7 lines, the top line has 1
	// star, the next row 2 more (ie 3), rows increase by 2 until the bottom row has
	// 13 stars as follows:
	/*
	      *
	     ***
	    *****
	   *******
	  *********
	 ***********
	 */
	// PROBLEM: The program fails to print out the pyramid.
	/*REASON: There are mistakes in the inner loop, especially in the if-statement.
	 With that if-statement, the program will not print a perfect pyramid.
	 Moreover, even if the if-statement is correct, the last asterisk in the last
	 row will not be printed as the m variable in the inner loop is less than 13,
	 so only 12 asterisks are printed in the last row. We also know that the
	 variable int x is to indicate the last position of the asterisk in each row,
	 and int x = 6 is another issue because the topmost asterisk will not be in
	 the appropriate position. The int y also has a problem in it where it is wrong
	 to increment it by 1 according to what we should use in the correct if-else statement.
	*/
	/*SOLUTION: We can first change int m = 13 into int m = 14. Next, change the initial value of 
	 int x = 6 to int x = 7. Then, the variable y should be decrement by 1 instead. So, we change from y++ to y--.
	 The two if-statement we should write if-else statement
	 instead. It should look like this: if (j<y || j>(m-y)) System.out.print(" ");
				else if (j<=x) System.out.print("*");
				else System.out.print("*");
	*/
	private static void loop11() {
		int k = 7;
		int m = 13;
		int x = 6;
		int y = x;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				if (j < x)
					System.out.print(" ");
				if ((j >= x) && (j <= y))
					System.out.print("*");
			}
			System.out.println();
			x++;
			y++;
		}
	}

	// Search a String array for a particular String. If the search String is found
	// return true, else return false.
	// PROBLEM: When a particular String is found, it returns true but when it is
	// not found, ArrayIndexOutofBound occurs instead of returning a false
	/*REASON: The total length of the array is 9, the index of the array starts
	 from 0 and to 8, which gives the total of nine. Therefore, the for loop
	 condition makes it error to search for String which is not in the array. It
	 is because when the i is less than a.length, it lets the loop to also go
	 through from index 8 to index 9, which is supposed to stop since index 9 does
	 not exist.
	*/
	/*SOLUTION: Edit the i<a.length into i<a.length-1 to prevent
	 ArrayIndexOutOfBoundException since the last index 8 is the last index, after
	 the loop goes through index 7 to index 8, it simply stops at index 8. For
	 better purpose, it is better to not use == to compare for strings. Instead,
	 use .equals, as == can only be used to compare for strings if only all
	 strings are hard coded.
	*/
	private static boolean loop12(String[] a, String find) {
		int i;
		for (i = 0; i < a.length && a[i] != find; i++)
			;
		return (a[i] == find);
	}

	private static void runLoops() {
		loop1();
		System.out.println();
		loop2();
		System.out.println();
		loop3();
		System.out.println();
		loop4();
		System.out.println();
		loop5();
		System.out.println();
		loop6();
		System.out.println();
		loop7();
		System.out.println();
		loop8();
		System.out.println();
		loop9();
		System.out.println();
		loop10();
		System.out.println();
		loop11();
		System.out.println();
		System.out.println(loop12(greetings, "zdravstvuyte"));
		System.out.println();
		System.out.println(loop12(greetings, "merhaba"));

	}

	public static void main(String[] args) {
		runLoops();
	}

}
