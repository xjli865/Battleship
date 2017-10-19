package battleship;

/**
 * Represents the empty sea type.
 * 
 * @author Xiaojing Li
 */
public class EmptySea extends Ship {

	/**
	 * Sets the inherited length variable to 1, and initializes the hit array.
	 */
	public EmptySea() {
		this.length = 1;
		hit = new boolean[4];
	}

	/**
	 * Overrides the <code>getLength</code> method in ship class.
	 */
	@Override
	int getLength() {
		return this.length;
	}

	/**
	 * Overrides <code>shootAt(int row, int column)</code> that is inherited
	 * from Ship, and always returns false to indicate that nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	/**
	 * Overrides <code>isSunk()</code> that is inherited from Ship, and always
	 * returns false to indicate that you didn't sink anything.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Overrides the <code>getShipType</code> method in ship class so that it is
	 * "empty sea."
	 */
	@Override
	String getShipType() {
		return "empty sea";
	}

	/**
	 * Override the <code>toString</code> method so that empty sea can return
	 * the single string character for its type.
	 */
	@Override
	public String toString() {
		return "-";
	}

}
