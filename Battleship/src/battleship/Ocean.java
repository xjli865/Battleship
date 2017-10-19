package battleship;

import java.util.Random;

/**
 * Represents the "ocean."
 * 
 * @author Xiaojing Li
 */
public class Ocean {

	/**
	 * Used to quickly determine which ship is in any given location
	 */
	Ship[][] ships = new Ship[10][10];

	int shotsFired;
	int hitCount;

	/**
	 * used to determine if the location in the ocean was hit.
	 */
	boolean[][] getHit = new boolean[10][10];

	/**
	 * An array contains ten <code>Ship</code>objects
	 */
	Ship[] tenShips = new Ship[10];

	/**
	 * Creates an "empty" ocean, where it fills the <code>ships</code> array
	 * with <code>EmptySeas</code>.
	 */
	public Ocean() {
		this.shotsFired = 0;
		this.hitCount = 0;
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships.length; j++) {
				ships[i][j] = new EmptySea();
			}
		}
	}

	/**
	 * Places all ten ships randomly on the (initially empty) ocean.
	 */
	void placeAllShipsRandomly() {
		Random random = new Random();
		boolean horizontal;
		int row;
		int column;
		Battleship battbleship = new Battleship();
		tenShips[0] = battbleship;
		Cruiser cruiser1 = new Cruiser();
		Cruiser cruiser2 = new Cruiser();
		tenShips[1] = cruiser1;
		tenShips[2] = cruiser2;
		Destroyer destroyer1 = new Destroyer();
		Destroyer destroyer2 = new Destroyer();
		Destroyer destroyer3 = new Destroyer();
		tenShips[3] = destroyer1;
		tenShips[4] = destroyer2;
		tenShips[5] = destroyer3;
		Submarine sunmarine1 = new Submarine();
		Submarine sunmarine2 = new Submarine();
		Submarine sunmarine3 = new Submarine();
		Submarine sunmarine4 = new Submarine();
		tenShips[6] = sunmarine1;
		tenShips[7] = sunmarine2;
		tenShips[8] = sunmarine3;
		tenShips[9] = sunmarine4;
		for (int i = 0; i < tenShips.length; i++) {
			do {
				horizontal = random.nextBoolean();
				row = random.nextInt(10);
				column = random.nextInt(10);
			} while (!(tenShips[i].okToPlaceShipAt(row, column, 
					horizontal, this)));
			tenShips[i].placeShipAt(row, column, horizontal, this);
		}
	}

	/**
	 * Checks if the given location contains a ship.
	 * 
	 * @param row
	 *            The row of the location
	 * @param column
	 *            The column of the location
	 * @return The results after checking whether the location contains a ship
	 */
	boolean isOccupied(int row, int column) {
		if (ships[row][column] instanceof EmptySea) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if the given location contains a "real" ship that is still afloat.
	 * 
	 * @param row
	 *            The row of the location
	 * @param column
	 *            The column of the location
	 * @return The results after checking whether the location contains a "real"
	 *         ship that is still afloat
	 */
	boolean shootAt(int row, int column) {
		getHit[row][column] = true;
		shotsFired += 1;
		if (this.ships[row][column].shootAt(row, column)) {
			if (ships[row][column] instanceof EmptySea) {
				return false;
			} else {
				if (ships[row][column].isSunk()) {
					return false;
				} else {
					hitCount += 1;
					return true;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Gets the number of shots fired in this game.
	 * 
	 * @return The number of shots.
	 */
	int getShotsFired() {
		return shotsFired;
	}

	/**
	 * Gets the number of hits recorded on ships in the game.
	 * 
	 * @return The number of hits
	 */
	int getHitCount() {
		return hitCount;
	}

	/**
	 * Check if the game over.
	 * 
	 * @return The results after checking whether all ships are sunk.
	 */
	boolean isGameOver() {
		for (int i = 0; i < tenShips.length; i++) {
			if (!(tenShips[i].isSunk())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the actual 10x10 array of ships.
	 * 
	 * @return The array of ships.
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}

	/**
	 * Prints the ocean with ships' status shown in its single string character.
	 */
	void print() {
		int[] rowDisplay = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println("   " + java.util.Arrays.toString(rowDisplay).
				replace(",", " ").replace("[", "").replace("]", ""));
		int columnDisplay = 0;
		for (int i = 0; i < ships.length; i++) {
			System.out.print(columnDisplay + "  ");
			columnDisplay += 1;
			for (int j = 0; j < ships.length; j++) {
				if (getHit[i][j]) {
					System.out.print(ships[i][j] + "  ");
				} else {
					System.out.print(ships[i][j].toString().replace("-", ".").
							replace("S", ".") + "  ");
				}
			}
			System.out.println("");
		}

	}
}
