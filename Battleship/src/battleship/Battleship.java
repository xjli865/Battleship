package battleship;

/**
 * Represents the battleship type.
 * 
 * @author Xiaojing Li
 */
public class Battleship extends Ship {

	/**
	 * Sets the inherited length variable to 4, and initializes the hit array.
	 */
	public Battleship() {
		this.length = 4;
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
	 * Overrides the <code>getShipType</code> method in ship class so that it is
	 * "battleship."
	 */
	@Override
	String getShipType() {
		return "battleship";
	}

}
