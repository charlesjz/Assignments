package superConstructor;

//Name: Kelly Weiling Zou
//NetID: 16KWZ
//Student Number: 20061148

import java.util.ArrayList;
import java.util.Scanner;

public class Assignment1_16KWZ {
	
	static int sumComputerTurnScore=0;
	
	public static void main(String[] args) {
		
		System.out.println("Welcome player! \nThis is the Game of Pig. \nWe will start with your turn first.");
		
		int playerGameScore = 0;
		int computerGameScore = 0;
		
		diceValues(playerGameScore, computerGameScore);
	}
	
	public static void diceValues(int playerGameScore, int computerGameScore) {
		ArrayList<String> diceWords = new ArrayList<String>();
		
		diceWords.add("");
		diceWords.add("one");
		diceWords.add("two");
		diceWords.add("three");
		diceWords.add("four");
		diceWords.add("five");
		diceWords.add("six");
		
		playerTurn(diceWords, playerGameScore, computerGameScore);
		
	}
	
	public static void playerTurn(ArrayList<String> diceWords, int playerGameScore, int computerGameScore){
		
		int diceNum1 = (int)(Math.random()*6 + 1);
		int diceNum2 = (int)(Math.random()*6 + 1);
		
		String diceWord1 = diceWords.get(diceNum1);
		String diceWord2 = diceWords.get(diceNum2);
		
		System.out.println("\nPlayer's turn: ");
		System.out.println("Player rolled the numbers " + diceWord1 + " and " + diceWord2 + ".");
		
		playerGameSum(diceWords, playerGameScore, computerGameScore, diceNum1, diceNum2);
	}

	public static void playerGameSum(ArrayList<String> diceWords, int playerGameScore, int computerGameScore, int diceNum1, int diceNum2) {
		int playerTurnScore = diceNum1 + diceNum2;
		
		if (playerGameScore >= 100) {
			finalWinner(playerGameScore, computerGameScore);
		}
		
		if (diceNum1 == 6 && diceNum2 == 6) {
			playerTurnScore = 0;
			playerGameScore = 0;
			System.out.println("DOUBLE SIXES!");
			System.out.println("Player's turn sum is: " + playerTurnScore + " and game sum would be: " + playerGameScore + ".");
			sumComputerTurnScore=0;
			computerTurn(diceWords, playerGameScore, computerGameScore);
		}
		
		else if (diceNum1 == 6 || diceNum2 == 6) {
			playerTurnScore = 0;
			System.out.println("TURN OVER! Turn sum is zero and game sum is " + playerGameScore + "!");
			sumComputerTurnScore=0;
			computerTurn(diceWords, playerGameScore, computerGameScore);
		}
		
		else if (diceNum1 == diceNum2 && diceNum1 != 6 && diceNum2 != 6) {
			playerTurnScore = (diceNum1 + diceNum2)*2;
			System.out.println("DOUBLES!");
			
			if ((playerGameScore + playerTurnScore) >= 100) {
				playerGameScore = playerGameScore + playerTurnScore;
				System.out.println("Player's turn sum is: " + playerTurnScore + " and game sum would be: " + playerGameScore + ".");
				finalWinner(playerGameScore, computerGameScore);
			}
		}
		
		playerGameScore = playerGameScore + playerTurnScore;
		System.out.println("Player's turn sum is: " + playerTurnScore + " and game sum would be: " + playerGameScore + ".");

		if (playerGameScore >= 100) {
			finalWinner(playerGameScore, computerGameScore);
		}
		
		playerDecision(diceWords, playerGameScore, computerGameScore, diceNum1, diceNum2);
		
	}
	
	public static void playerDecision(ArrayList<String> diceWords, int playerGameScore, int computerGameScore, int diceNum1, int diceNum2) {
		System.out.println("Roll again? (Enter 'y' or 'n'): ");
		Scanner userDecision = new Scanner(System.in);
		String userInput = userDecision.nextLine();
		
		if (userInput.equals("y") || userInput.equals("Y")) {
			playerTurn(diceWords, playerGameScore, computerGameScore);
			System.out.println("hello");
		}
		
		else if (userInput.equals("n") || userInput.equals("N")) {
			sumComputerTurnScore=0;
			computerTurn(diceWords, playerGameScore, computerGameScore);
		}
	}
	
	public static void computerTurn(ArrayList<String> diceWords, int playerGameScore, int computerGameScore) {
		
		int diceNum1 = (int)(Math.random()*6 + 1);
		int diceNum2 = (int)(Math.random()*6 + 1);
		
		String diceWord1 = diceWords.get(diceNum1);
		String diceWord2 = diceWords.get(diceNum2);
		
//		int computerTurnScore = diceNum1 + diceNum2;
		
		while (sumComputerTurnScore <=30) {
//		while (computerTurnScore <=30) {
			System.out.println("\nComputer's turn: ");
			System.out.println("Computer rolled the numbers " + diceWord1 + " and " + diceWord2 + ".");
			////
			System.out.println("*****sumComputerTurnScore="+sumComputerTurnScore);
			////
			computerGameSum(diceWords, playerGameScore, computerGameScore, diceNum1, diceNum2);
		}
		sumComputerTurnScore=0;
		playerTurn(diceWords, playerGameScore, computerGameScore);

	}
	
	public static void computerGameSum(ArrayList<String> diceWords, int playerGameScore, int computerGameScore, int diceNum1, int diceNum2) {
		int computerTurnScore = diceNum1 + diceNum2;
		
		if (computerGameScore >= 100) {
			finalWinner(playerGameScore, computerGameScore);
		}
		
		if (diceNum1 == 6 && diceNum2 == 6) {
			computerTurnScore = 0;
			computerGameScore = 0;
			System.out.println("DOUBLE SIXES!");
			System.out.println("Computer's turn sum is: " + computerTurnScore + " and game sum would be: " + computerGameScore + ".");
			playerTurn(diceWords, playerGameScore, computerGameScore);
		}
		
		else if (diceNum1 == 6 || diceNum2 == 6) {
			computerTurnScore = 0;
			System.out.println("TURN OVER! Turn sum is zero and game sum is " + computerGameScore + "!");
			playerTurn(diceWords, playerGameScore, computerGameScore);
		}
		
		else if (diceNum1 == diceNum2 && diceNum1 != 6 && diceNum2 != 6) {
			computerTurnScore = (diceNum1 + diceNum2)*2;
			System.out.println("DOUBLES!");
			
			if ((computerGameScore + computerTurnScore) >= 100) {
				computerGameScore = computerGameScore + computerTurnScore;
				System.out.println("Computer's turn sum is: " + computerTurnScore + " and game sum would be: " + computerGameScore + ".");
				finalWinner(playerGameScore, computerGameScore);
			}
		}
		computerGameScore = computerGameScore + computerTurnScore;
		System.out.println("Computer's turn sum is: " + computerTurnScore + " and game sum would be: " + computerGameScore + ".");
		////
		sumComputerTurnScore+=computerTurnScore;
		////

		if (computerGameScore >= 100) {
			finalWinner(playerGameScore, computerGameScore);
		}
		computerTurn(diceWords, playerGameScore, computerGameScore);
	}
	
	public static void finalWinner(int playerGameScore, int computerGameScore) {
		
		System.out.println("Player's sum is: " + playerGameScore + ", Computer's sum is: " + computerGameScore + ".");
		
		if (playerGameScore > computerGameScore) {
			System.out.println("\n*****PLAYER WINS!!*****");
			System.exit(0);
		}
		
		else if (playerGameScore < computerGameScore) {
			System.out.println("\n*****COMPUTER WINS!!*****");
			System.exit(0);
		}
		
	}

}

