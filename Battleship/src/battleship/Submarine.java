package battleship;

/**
 * Represents the submarine type.
 * 
 * @author Xiaojing Li
 */
public class Submarine extends Ship {

	/**
	 * Sets the inherited length variable to 1, and initializes the hit array.
	 */
	public Submarine() {
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
	 * Overrides the <code>getShipType</code> method in ship class so that it is
	 * "submarine."
	 */
	@Override
	String getShipType() {
		return "submarine";
	}
}
