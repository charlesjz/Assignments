package oct12;

import java.io.IOException;
import java.util.Random;

// This class allows a user to play the 2 dice variation of the Game of Pig where sixes are
// "bad" against the computer.  See the assignment statement for the rules of the game.
// by Prof. Alan McLeod

public class GameOfPig {

	private static Random generator = new Random(System.currentTimeMillis());
	private static final int GAME_LIMIT = 100;
	private static final int COMPUTER_TURN_LIMIT = 30;
	
	// Returns the value for a single dice roll.
	private static int rollDice() {
		return generator.nextInt(6) + 1;
	} // end rollDice
	
	// Returns the name of the dice roll as a word.
	private static String getRollName(int roll) {
		String[] names = {"one", "two", "three", "four", "five", "six"};
		return names[roll - 1];
	} // end getRollName

	// Obtains and returns a single character as provided by the user. If the user enters
	// more than one character the extra characters are ignored. If he or she does not 
	// provide any characters then the null character is returned.
	private static char getChar() {
		byte[] buffer = new byte[100];
		int numRead = 0;
		try {
			numRead = System.in.read(buffer);
		} catch (IOException e) {
		}
		if (numRead > 0)
			return (char)buffer[0];
		return '\0';
	} // end getChar
	
	// Ask the human player if he or she wants to roll again. The player must supply y, Y, n, or N.
	// If the player supplies anything else the player will be re-prompted.
	private static boolean playerRollAgain() {
		char response = '?';
		while (!(response == 'n' || response == 'N' || response == 'y' || response == 'Y')) {
			System.out.print("Roll again? (Enter \'y\' or \'n\'): ");
			response = getChar();
			if (response == 'n' || response == 'N')
				return false;
			else if (response == 'y' || response == 'Y')
				return true;
			else
				System.out.print("Illegal entry, please try again. ");
		}
		return true;
	} // end playerRollAgain
	
	// Reports the current dice roll for both dice and for either player.
	private static void reportRoll(String who, int roll1, int roll2) {
		System.out.println(who + " rolled " + getRollName(roll1) + " + " + getRollName(roll2));
	} // end reportRoll
	
	// Reports the current turn sum as well as the potential game sum for either player.
	private static void reportSums(String who, int turnSum, int gameSum) {
		System.out.println(who + "\'s turn sum is: " + turnSum + 
				" and game sum would be: " + (gameSum + turnSum) + ".");		
	} // end reportSums
	
	// Checks to see if either or both dice are a six or doubles.
	// The turn sum is returned. A zero return means a single six was rolled and
	// the turn sum will be zero.
	private static int checkDice(int dice1, int dice2, int gameScore) {
		if (dice1 + dice2 == 12) {
			System.out.println("DOUBLE SIXES!");
			return -gameScore;
		}
		else if (dice1 == 6 || dice2 == 6) {
			System.out.println("TURN OVER! Turn sum is zero!");
			return 0;
		}
		else if (dice1 == dice2) {
			System.out.println("DOUBLES!");
			return 2 * (dice1 + dice2);
		}
		return dice1 + dice2;
	} // end checkDice
	
	// Reports the game sums for both players.
	private static void reportSums(int playerSum, int computerSum) {
		System.out.print("\nPlayer\'s sum is: " + playerSum + 
				", Computer\'s sum is: " + computerSum + ".");
	} // end reportSums
	
	// Executes the computer's turn. If it can, the computer will roll until its turn sum
	// passes 30. The method returns the resulting turn sum for the computer.
	private static int computerTurn(int gameSum) {
		int stopLimit = COMPUTER_TURN_LIMIT;
		if (GAME_LIMIT - gameSum < COMPUTER_TURN_LIMIT)
			stopLimit = (GAME_LIMIT - gameSum);
		boolean turnContinue = true;
		int dice1, dice2;
		int turnSum = 0;
		int rollSum;
		while (turnContinue) {
			dice1 = rollDice();
			dice2 = rollDice();
			reportRoll("Computer", dice1, dice2);
			rollSum = checkDice(dice1, dice2, gameSum);
			if (rollSum <= 0)
				return rollSum;
			turnSum += rollSum;
			reportSums("Computer", turnSum, gameSum);
			if (dice1 == dice2)
				System.out.println("Computer must roll again!");
			else if (turnSum >= stopLimit)
				turnContinue = false;
		}
		return turnSum;
	} // end computerTurn
	
	// Executes the human player's turn. If possible, the turn will continue until the
	// player decides not to roll again. The method returns the resulting turn sum for the
	// human player.
	private static int playerTurn(int gameSum) {
		boolean turnContinue = true;
		int dice1, dice2;
		int turnSum = 0;
		int rollSum;
		while (turnContinue) {
			dice1 = rollDice();
			dice2 = rollDice();
			reportRoll("Player", dice1, dice2);
			rollSum = checkDice(dice1, dice2, gameSum);
			if (rollSum <= 0)
				return rollSum;
			turnSum += rollSum;
			reportSums("Player", turnSum, gameSum);
			if (dice1 == dice2)
				System.out.println("Player must roll again!");
			else if (turnSum + gameSum >= GAME_LIMIT)
				turnContinue = false;
			else
				turnContinue = playerRollAgain();
		}
		return turnSum;
	} // end playerTurn
	
	// This method executes the game. It loops until either the computer or the human player
	// has exceeded or matched a game sum of 100. It keeps track of the game sums for both players
	// and provides messages for who wins. To decrease possible confusion, the loop stops every turn,
	// until the user presses the enter key.
	private static boolean playGame() {
		int playerSum = 0;
		int computerSum = 0;
		int rounds = 1;
		@SuppressWarnings("unused")
		char dummy;
		while (playerSum < GAME_LIMIT && computerSum < GAME_LIMIT) {
			System.out.println("\nPlayer\'s turn:");
			playerSum += playerTurn(playerSum);
			reportSums(playerSum, computerSum);
			if (playerSum >= GAME_LIMIT) {
				System.out.println("\n*****PLAYER WINS!!*****");
				return true;
			} else {
				System.out.println("\n\nComputer\'s turn:");
				computerSum += computerTurn(computerSum);
				reportSums(playerSum, computerSum);
				if (computerSum >= GAME_LIMIT) {
					System.out.println("\n*****Computer wins, again.*****");
					return false;
				}
			}
			System.out.print(" Press <enter> to start round " + ++rounds + ".");
			// dummy is just used the empty the keyboard buffer of the enter key press.
			dummy = getChar();
		}
		return false;
	} // end playGame
	
	// main just invokes the playGame method.
	public static void main(String[] args) {
		playGame();
	} // end main

} // end GameOfPig class
