/* Name:
   SIM Student Number:
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExamMarksQuerySystem {

	// All statics variables and arrays declared here are visible to all the methods in this class.
	public static String[] menu = { "Display a student mark and grade", "Display average mark",
			"Edit a student exam and coursework record" };
	public static String[] studentName = {"Peter","John","Carl"};
	public static int[] cwMarks= {56,50,60};
	public static int[] examMarks= {78,77,88};

	public static void main(String[] args) {

		// load students data into array
		loadStudentsData();

		// menu driven application
		int choice = -1;

		while (choice != 0) {
			choice = Helper.getUserOption("Exam marks query system", menu);
			//choice = 2;
			if (choice == 1)
				displayAStudentScore();
			else if (choice == 2)
				displayAverageMarks();
			else if (choice == 3)
				editRecord();
		}
	}

	// You have access to the three public arrays: studentName, cwMarks and
	// examMarks in this method
	public static void displayAStudentScore() {
	
		// User enter student name
		String name = Helper.readString("Enter student name:");
		//String name = "Peter";
		int index = 0, cwMark = 0, examMark = 0;
		boolean found = false;
		
		for (int i = 0; i < studentName.length; i++) {
			// student name found
			if (studentName[i].equals(name)) {
				examMark = examMarks[i];
				cwMark = cwMarks[i];
				found = true;
				break;
			}
		}

		if (found) {
			// System display the student's coursework mark , exam mark and overall mark.
			// overall mark is 20% of coursework mark and 80% of exam marks
			// To pass - student must pass both coursework and exam (i.e. >=50%)
			Helper.line(50, "=");
			System.out.println("Student's grade");
			Helper.line(50, "-");

			double oaMarks = 0.2 * cwMark + 0.8 * examMark;
			
			char grade = 'F';
			if(cwMark>= 50 && examMark>=50){
				grade = getGrade(oaMarks);
			}
			
			System.out.printf("%-5s %-15s %-10s %-5s\n", "Exam", "Coursework", "Overall", "Grade");
			System.out.printf("%-5d %-15d %-10.2f %-5c\n", examMark, cwMark, oaMarks, grade);
			
		} else {
			System.out.println("Student name not found");
		}
	}

	public static char getGrade(double marks){
		
		char grade = 'F';
		if(marks>=85){
			grade = 'A';
		}else if(marks>=75){
			grade = 'B';
		}else if(marks>=65){
			grade = 'C';
		}else if(marks>=50){
			grade = 'D';
		}else if(marks>=45){
			grade = 'E';
		}
		return grade;
	}
	
	public static void displayAverageMarks() {

		Helper.line(50, "=");
		System.out.println("Average marks");
		Helper.line(50, "-");
		
		// compute average exam marks
		int total = 0;
		for(int i=0;i<examMarks.length;i++){
			total+=examMarks[i];
		}
		double examAve = ((double)total)/examMarks.length;
		
		// compute average cw marks
		total = 0;
		for(int i=0;i<cwMarks.length;i++){
			total+=cwMarks[i];
		}
		double cwAve = ((double)total)/cwMarks.length;
		
		// compute average overall marks	
		double total2 = 0;
		for(int i=0;i<examMarks.length;i++){
			total2+=0.2*cwMarks[i] + 0.8*examMarks[i];
		}
		double oaAve = (total2)/examMarks.length;
		
		System.out.printf("%-5s %-10s %-10s\n", "Exam", "Coursework", "Overall");
		System.out.printf("%-5.2f %-10.2f %-10.2f\n", examAve, cwAve, oaAve);
	}

	// We have access to the three public static arrays: studentName, cwMarks
	// and examMarks in this method
	public static void editRecord() {

		int i = Helper.readInt("Enter student array index (0 to "+(studentName.length-1)+"):");
		int newExamMarks = Helper.readInt("Enter new student exam marks:");
		int newCwMarks = Helper.readInt("Enter new student coursework marks:");
		
		cwMarks[i] = newCwMarks;
		examMarks[i] = newExamMarks;
		
		System.out.println("Student "+ studentName[i] + " exam and coursework marks updated");
	}
	

	// We have access to the three public static arrays: studentName, cwMarks
	// and examMarks in this method
	public static void loadStudentsData() {

		try {
			Scanner sc = new Scanner(new File("marks.txt"));
			int counter = 0;
			//first line is the number of records
			int numOfRecords = Integer.parseInt(sc.nextLine());
			studentName = new String[numOfRecords];
			cwMarks = new int[numOfRecords];
			examMarks = new int[numOfRecords];
			
			while (sc.hasNextLine()) {
				studentName[counter] = sc.nextLine();
				examMarks[counter] = Integer.parseInt(sc.nextLine());
				cwMarks[counter] = Integer.parseInt(sc.nextLine());
				//System.out.println(studentName[counter]);
				counter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
