package battleship;

/**
 * Represents the cruiser type
 * 
 * @author Xiaojing Li
 */
public class Cruiser extends Ship {

	/**
	 * Sets the inherited length variable to 3, and initializes the hit array.
	 */
	public Cruiser() {
		this.length = 3;
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
	 * "cruiser."
	 */
	@Override
	String getShipType() {
		return "cruiser";
	}

}