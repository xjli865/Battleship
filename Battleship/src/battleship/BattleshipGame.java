package battleship;

import java.util.Scanner;

/**
 * This is a solo version of a battleship game.
 * <p>
 * In this game, computer places the ships, and the human attempts to sink them,
 * where ships are hidden from the human. <i>Ten</i> ships of different types of
 * ships are placed in the sea. There are one ship of battleship, two ships of
 * cruiser, three ships of destroyer, and four ships of submarines. These ships
 * are placed either horizontally or vertically.
 * <p>
 *
 * The human player tries to hit the ships, by calling out a row and column
 * number. The computer responds with one bit of information--hit" or "miss."
 * After each shot, the computer redisplays the ocean with the new information.
 * <p>
 *
 * To win the game, you need to sunk all ten ships. The object is to sink the
 * fleet with as few shots as possible
 * <p>
 * 
 * @author Xiaojing Li
 * @version 0
 */
public class BattleshipGame {

	/**
	 * Creates an ocean instance.
	 */
	Ocean theOcean = new Ocean();

	/**
	 * The main method just creates a BattleshipGame object and calls its 
	 * <code>gameInstructions</code> method, the<code> print </code> and
	 * <code>placeAllShipsRandomly</code>methods in ocean instance, and its 
	 * <code>handleInputValue</code>.
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		BattleshipGame myGame = new BattleshipGame();
		myGame.gameInstructions();
		myGame.theOcean.print();
		myGame.theOcean.placeAllShipsRandomly();
		myGame.handleInputValue();
	}

	/**
	 * Prints Battleship game instructions.
	 */
	void gameInstructions() {
		System.out.println("|        Battleship Game Instructiosn      |");
		System.out.println("--------------------------------------------");
		System.out.println("| This game allows players to make guesses |");
		System.out.println("| of ships locations in the ocean and sink |");
		System.out.println("| them. To hit the ship, you need to input |");
		System.out.println("| in format as row, column(e.g 1,2) to shot|");
		System.out.println("| fires. If you hit a parts of ship, you   |");
		System.out.println("| will recieve 'Hit!' message, otherwise   |");
		System.out.println("| you will recieve 'Miss!' message. You win|");
		System.out.println("| the game when you shot all ten ships.    |");
		System.out.println("--------------------------------------------\n");
	}

	/**
	 * Checks if the user's inputs are valid/legal.
	 * 
	 * @param ans
	 *            The user's inputs
	 * @return The response after checking user's inputs.
	 */
	boolean checkInputValue(String ans) {
		int part1;
		int part3;
		if (ans.length() == 3 && ans.contains(",")) {
			String[] part = ans.split(",");
			part1 = Integer.parseInt(part[0]);
			part3 = Integer.parseInt(part[1]);
			if (!(part1 >= 0 && part1 <= 9 && part3 >= 0 && part3 <= 9)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Handles user's inputs and passes them as parameters to other methods.
	 */
	void handleInputValue() {
		if (!(theOcean.isGameOver())) {
			System.out.println("Which location would you like to hit?"
					+ "(Enter as: row, column)");
			Scanner userInput = new Scanner(System.in);
			String ans = userInput.nextLine().trim().replace(" ", "");
			if (checkInputValue(ans)) {
				String[] part = ans.split(",");
				int part1 = Integer.parseInt(part[0]);
				int part3 = Integer.parseInt(part[1]);
				theOcean.shootAt(part1, part3);
				theOcean.print();
				if (theOcean.ships[part1][part3] instanceof EmptySea) {
					System.out.println("Miss!");
				} else {
					System.out.println("Hit!");
				}
				playContinue();
			} else {
				handleInputValue();
			}
		} else {
			System.out.println("The game is over");
			int numberOfHits = theOcean.shotsFired;
			System.out.println("You had " + numberOfHits + " hits.");
			playAgain();
		}
	}

	/**
	 * Asks if the player want to continue the game.
	 */
	void playContinue() {
		System.out.println("Do you want to continue?");
		Scanner response = new Scanner(System.in);
		String myAns = response.nextLine();
		String ch = myAns.charAt(0) + "";
		if (ch.equals("y") || ch.equals("Y")) {
			handleInputValue();
		} else if (ch.equals("n") || ch.equals("N")) {
			if (theOcean.isGameOver()) {
				System.out.println("You win the Game.");
			} else {
				System.out.println("You didn't win the game.");
				int numberOfHits = theOcean.shotsFired;
				System.out.println("You had " + numberOfHits + " hits.");
				playAgain();
			}
		} else {
			playContinue();
		}
	}

	/**
	 * Asks if the player likes to play again after the game is over.
	 */
	void playAgain() {
		System.out.println("Do you want to play again?");
		Scanner response = new Scanner(System.in);
		String myAns = response.nextLine();
		String ch = myAns.charAt(0) + "";
		if (ch.equals("y") || ch.equals("Y")) {
			theOcean = new Ocean();
			gameInstructions();
			theOcean.print();
			theOcean.placeAllShipsRandomly();
			handleInputValue();
		} else if (ch.equals("n") || ch.equals("N")) {
			System.exit(0);
		} else {
			playAgain();
		}
	}

}
