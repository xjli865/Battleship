package battleship;

/**
 * Represents the destroyer type.
 * 
 * @author Xiaojing Li
 */
public class Destroyer extends Ship {

	/**
	 * Sets the inherited length variable to 2, and initializes the hit array.
	 */
	public Destroyer() {
		this.length = 2;
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
	 * "destroyer."
	 */
	@Override
	String getShipType() {
		return "destroyer";
	}
}
