import java.util.ArrayList;
import java.util.Scanner;

public class DriverExam {
	public static void main (String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcomt to the Test-Grade-O-Nator \nEnter \"Y\" to start the program >> ");	
		
		//ensures that the user wants to run the program 
		if(!scan.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Program terminated");
			scan.close();
			System.exit(0);
		}
		
		//loop that executes for the user to enter the correct answers 
		String breakInput = "Done";
		int answerCount = 1;
		System.out.println("Please enter the correct answers to the test you wish to grade: \n(enter up to 20 of the options \"A\" \"B\" \"C\" \"D\" or " + breakInput + " when the test is complete)");
		ArrayList<Character> correctAnswers = new ArrayList<Character>();
		for (int i=0; i<20; i++) {
			String userInput = null;
			//loop that ensures that the proper input was entered
			boolean acceptableInput = false;
			while(!acceptableInput) {
				System.out.print("Please enter answer for question " + answerCount + " >> ");
				userInput = scan.nextLine();
				if (userInput.equalsIgnoreCase(breakInput)) {
					break;
				}
				else if (userInput.equalsIgnoreCase("a") || userInput.equalsIgnoreCase("b") || userInput.equalsIgnoreCase("c") || userInput.equalsIgnoreCase("d")) {
					acceptableInput = true;
				}	
				else {
					System.out.println("Invalid input: " + userInput);
				}
			}
            //breaks the for loop if the entry is the quit value
            if(userInput.equalsIgnoreCase(breakInput)){
               break;			
            }
            Character temp = userInput.charAt(0);
            correctAnswers.add(temp);
            ++answerCount;
		}
		
		//loop that executes for the user to enter the student responses
		answerCount = 1;
		System.out.println("Please enter the student's responses: \n(enter options \"A\" \"B\" \"C\" \"D\" or \"X\" if the student left the quesiton blank)");
		ArrayList<Character> studentAnswers = new ArrayList<Character>();
		for (int i=0; i<correctAnswers.size(); i++) {
			String userInput = null;
			//loop that ensures that the proper input was entered
			boolean acceptableInput = false;
			while(!acceptableInput) {
				System.out.print("Please enter student response for question " + answerCount + " >> ");
				userInput = scan.nextLine();
				 if (userInput.equalsIgnoreCase("a") || userInput.equalsIgnoreCase("b") || userInput.equalsIgnoreCase("c") || userInput.equalsIgnoreCase("d") || userInput.equalsIgnoreCase("x")) {
					acceptableInput = true;
				}	
				else {
					System.out.println("Invalid input: " + userInput);
				}
			}
            Character temp = userInput.charAt(0);
            studentAnswers.add(temp);
            ++answerCount;         
		}
		
		//checks to see if the student passed
		if(passed(correctAnswers, studentAnswers))
			System.out.println("\nThe student passed the exam.");
		else
			System.out.println("\nThe student failed the exam.");
		
		//loop that allows the user to choose if they want to see a score breakdown and ensures that a proper choice was made
		String userInput = null;
		boolean acceptableInput = false;
		while(!acceptableInput) {
			System.out.print("Would you like to see a breakdown of thier score [y/n]? >> ");
			userInput = scan.nextLine();
			 if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n")) {
				acceptableInput = true;
			}	
			else {
				System.out.println("Invalid input: " + userInput);
			}
		}
		
		 //prints the additional information if the user wants to see a breakdown of the test
		 if (userInput.equalsIgnoreCase("y")) {
			System.out.println("Total correct answers:   " + totalCorrect(correctAnswers, studentAnswers) + " / " + correctAnswers.size());
			System.out.println("Total incorrect answers: " + totalIncorrect(correctAnswers, studentAnswers) + " / " + correctAnswers.size());
			quesitonsMissed(correctAnswers, studentAnswers);
		 }
		
		 //closes scanner before the program ends
		 scan.close();
	}	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//method that returns if the user passed the exam or not
	public static boolean passed(ArrayList<Character> key, ArrayList<Character> responses) {
		int correctAnswers = totalCorrect(key, responses);
		int questions = key.size();
		int percentCorrect = (100*correctAnswers)/questions;
		int passingScore = 75;
		if (percentCorrect >= passingScore)
			return true;
		else
			return false;
	}
	
	
	//method that counts the amount of correct responses
	public static int totalCorrect(ArrayList<Character> key, ArrayList<Character> responses) {
		int correctAnswers = 0;
		for(int i=0; i<key.size(); i++) {
			if(key.get(i).equals(responses.get(i)))
				correctAnswers++;
		}
		return correctAnswers;
	}
	
	//method that returns the amount of incorrect answers
	public static int totalIncorrect(ArrayList<Character> key, ArrayList<Character> responses) {
		int correctAnswers = totalCorrect(key, responses);
		int incorrectAnswers = key.size() - correctAnswers;
		return incorrectAnswers;
	}
	
	//prints the questions that the user missed in order 
	public static void quesitonsMissed(ArrayList<Character> key, ArrayList<Character> responses) {
			System.out.println("Incorrect responses: ");
			System.out.printf("%-12s %-17s %-17s %n", "Question", "Correct Answer", "Student Response");		
		for(int i=0; i<key.size(); i++) {
			int questionNum = i+1;
			if(!key.get(i).equals(responses.get(i))) {
				System.out.printf("%-12s %-17s %-17s %n", questionNum, key.get(i), responses.get(i));
			}
		}
	}
}
