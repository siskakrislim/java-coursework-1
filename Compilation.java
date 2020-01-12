import java.util.Random;

public class Compilation {

	public static void main(String[] args) {
		loop10();
		loop3();
		loop7();
		System.out.println(loop14(greetings, "zdravstvuyte"));
		System.out.println(loop12(greetings, "nem ho"));
		int n1=4;
		int starCounter=1;
		loop11();
		loop1();
		
		
	}private static boolean loop12(String[]a, String find) {
		int i;
		for (i=0; i<a.length-1 && a[i]!=find; i++);
			return (a[i]==find);
	
	}static String greetings[] = {"hello", "hallo", "bok", "bonjour", "zdravstvuyte", "namaskaar", "hai", "nee how", "ne ho"};
	private static void loop11() {
		int k = 7;
		int m = 14;
		int x = 7;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				if (j==x) {System.out.print("*");
				break;}
				else if (j<(k-i)) System.out.print(" ");
				else {
					System.out.print("*");
				}
			}
			
			System.out.println();
			x++;
			
		}
	}private static void loop1() {
		int k = 7;
		int m = 14;
		int x = 7; int y = x;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				if (j<y || j>(m-y)) System.out.print(" ");
				else if (j<=x) System.out.print("*");
				else System.out.print("*");
			}
			System.out.println();
			x++;
			y--;
		}
	}private static void loop7(){
		Random r = new Random();
		int i = 0;
		int j = 10;
		while (true){
			i = r.nextInt(11);
			System.out.println("i = "+i+" and j = "+j);
			if (i==j)break;
			
		}
		System.out.println("out of loop");
	}private static void loop3() {
		int i = 0;
		do {
			i++;
			System.out.println("In loop "+i);
		} while(i < 10);
		System.out.println("Out of loop");
	}private static void loop9() {
		int i = 0;
		int j = 0;
		while(i < 10) {
			System.out.println("In loop 1 "+i);
			while(j < 5) {
				System.out.println("In loop 2 "+i);
				j++;
			}
			j=0;
			i++;
		}
		System.out.println("Out of loops");
	}private static void loop10() {
		int k = 9;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}private static boolean loop14(String[]a, String find) {
		int i;
		for (i=0; i<a.length-1 && !(a[i].equals(find)); i++);
			return (a[i].equals(find));
	}
	}



