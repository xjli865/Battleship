package battleship;

/**
 * Represents ships that are placed in the ocean.
 * 
 * @author Xiaojing Li
 */
public abstract class Ship {
	int bowRow;
	int bowColumn;
	int length;
	boolean horizontal;

	/**
	 * An array of booleans telling whether that part of the ship has been hit
	 */
	boolean[] hit;

	/**
	 * Gets the length of the ship.
	 * 
	 * @return Length of this particular ship.
	 */
	abstract int getLength();

	/**
	 * Gets the row contains bow of the ship.
	 * 
	 * @return The row contains bow of the ship.
	 */
	int getBowRow() {
		return this.bowRow;
	}

	/**
	 * Gets the column contains bow of the ship.
	 * 
	 * @return The column contains bow of the ship.
	 */
	int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * Checks if the ship is horizontal.
	 * 
	 * @return The response as true if ship is oriented horizontally.
	 */
	boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * Sets the value of bowRow.
	 * 
	 * @param row
	 *            The row contains bow of the ship.
	 */
	void setBowRow(int row) {
		bowRow = row;
	}

	/**
	 * Sets the value of bowColumn.
	 * 
	 * @param column
	 *            The column contains bow of the ship.
	 */
	void setBowColumn(int column) {
		bowColumn = column;
	}

	/**
	 * Sets the value of the instance variable horizontal.
	 * 
	 * @param horizontal
	 *            The answer of whether horizontal.
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Gets the ship type.
	 * 
	 * @return The type of this ship.
	 */
	abstract String getShipType();

	/**
	 * Checks if it is okay to put a ship of this length with its bow in this
	 * location, with the given orientation.
	 * 
	 * @param row
	 *            The row in the location.
	 * @param column
	 *            The column in the location.
	 * @param horizontal
	 *            The orientation of the ship.
	 * @param ocean
	 *            The ocean where to place the ship.
	 * @return The results of whether the given location is all right to place
	 *         the ship.
	 */
	boolean okToPlaceShipAt(int row, int column, 
			boolean horizontal, Ocean ocean) {
		if (row >= 0 && row <= 9 && column >= 0 && column <= 9) {
			if (horizontal) {
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < length + 1; j++) {
						if (row + i < 0 || column + j < 0 || 
								row + i > 9 || column + j > 9) {
							if (column + length > 9) {
								return false;
							} else {
								continue;
							}
						} else {
							if (!(ocean.ships[row + i][column + j] 
									instanceof EmptySea)) {
								return false;
							}
						}
					}
				}
				return true;
			} else {
				for (int j = -1; j < length + 1; j++) {
					for (int i = -1; i < 2; i++) {
						if (row + j < 0 || column + i < 0 || 
								row + j > 9 || column + i > 9) {
							if (row + length > 9) {
								return false;
							} else {
								continue;
							}
						} else {
							if (!(ocean.ships[row + j][column + i] 
									instanceof EmptySea)) {
								return false;
							}
						}
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Places the ship at given location in the ocean.
	 * 
	 * @param row
	 *            The row in the location.
	 * @param column
	 *            The column in the location.
	 * @param horizontal
	 *            The orientation of the ship.
	 * @param ocean
	 *            The ocean where to place the ship.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		if (horizontal) {
			for (int i = 0; i < length; i++) {
				ocean.ships[row][column + i] = this;
			}
		} else {
			for (int j = 0; j < length; j++) {
				ocean.ships[row + j][column] = this;
			}
		}
	}

	/**
	 * Checks if part of the ship that is not sunk occupies the given row and
	 * column, and make changes in the hit array.
	 * 
	 * @param row
	 *            The row in the location.
	 * @param column
	 *            The column in the location.
	 * @return The results of ship status on whether it was hit, but not yet
	 *         sunk.
	 */
	boolean shootAt(int row, int column) {
		if (!(isSunk())) {
			if (horizontal) {
				if (column - this.bowColumn < length) {
					hit[column - this.bowColumn] = true;
					return true;
				}
				return false;
			} else {
				if (row - this.bowRow < length) {
					hit[row - this.bowRow] = true;
					return true;
				}
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if the ship is sunk
	 * 
	 * @return The status of the ship
	 */
	boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Override the default method so that <code>toString</code> displays the
	 * single-character String which reflects the status of the ship
	 */
	@Override
	public String toString() {
		if (isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}
}
