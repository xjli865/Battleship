package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {
	Battleship exampleShip1 = new Battleship();
	Cruiser exampleShip2 = new Cruiser();
	Destroyer exampleShip3 = new Destroyer();
	Submarine exampleShip4 = new Submarine();
	EmptySea exampleShip5 = new EmptySea();

	@Test
	public void testGetColumn() {
		assertEquals(exampleShip1.getBowColumn(), exampleShip1.bowColumn);
		assertEquals(exampleShip2.getBowColumn(), exampleShip2.bowColumn);
		assertEquals(exampleShip3.getBowColumn(), exampleShip3.bowColumn);
	}

	@Test
	public void testGetBow() {
		assertEquals(exampleShip1.getBowRow(), exampleShip1.bowRow);
		assertEquals(exampleShip2.getBowRow(), exampleShip2.bowRow);
		assertEquals(exampleShip3.getBowRow(), exampleShip3.bowRow);
	}

	@Test
	public void testIsHorizontal() {
		assertEquals(exampleShip1.isHorizontal(), exampleShip1.horizontal);
		assertEquals(exampleShip2.isHorizontal(), exampleShip2.horizontal);
		assertEquals(exampleShip3.isHorizontal(), exampleShip3.horizontal);
	}

	@Test
	public void testSetBowRow() {
		exampleShip1.setBowRow(5);
		assertEquals(exampleShip1.bowRow, 5);
		exampleShip2.setBowRow(8);
		assertEquals(exampleShip2.bowRow, 8);
	}

	@Test
	public void testSetBowColumn() {
		exampleShip1.setBowColumn(5);
		assertEquals(exampleShip1.bowColumn, 5);
		exampleShip2.setBowColumn(8);
		assertEquals(exampleShip2.bowColumn, 8);
	}

	@Test
	public void testSetHorizontal() {
		exampleShip1.setHorizontal(true);
		assertTrue(exampleShip1.horizontal);
		exampleShip2.setHorizontal(false);
		assertFalse(exampleShip2.horizontal);
	}

	@Test
	public void testOkToPlaceShipAt() {
		Ocean creatOcean1 = new Ocean();
		assertTrue(exampleShip1.okToPlaceShipAt(2, 2, true, creatOcean1));
		assertTrue(exampleShip1.okToPlaceShipAt(0, 0, true, creatOcean1));
		assertFalse(exampleShip1.okToPlaceShipAt(9, 9, true, creatOcean1));
		Ocean createOcean2 = new Ocean();
		createOcean2.ships[0][0] = new Submarine();
		createOcean2.ships[0][1] = new Submarine();
		assertFalse(exampleShip1.okToPlaceShipAt(1, 1, true, createOcean2));
		assertFalse(exampleShip1.okToPlaceShipAt(1, 1, false, createOcean2));
		assertFalse(exampleShip1.okToPlaceShipAt(1, 2, true, createOcean2));
		assertTrue(exampleShip1.okToPlaceShipAt(9, 2, true, createOcean2));
		assertFalse(exampleShip1.okToPlaceShipAt(3, 8, true, createOcean2));
	}

	@Test
	public void placeShipAt() {
		Ocean creatOcean1 = new Ocean();
		exampleShip1.placeShipAt(0, 1, true, creatOcean1);
		assertEquals(creatOcean1.ships[0][1], exampleShip1);
		assertEquals(creatOcean1.ships[0][2], exampleShip1);
		assertEquals(creatOcean1.ships[0][3], exampleShip1);
		assertEquals(creatOcean1.ships[0][4], exampleShip1);
		exampleShip2.placeShipAt(3, 5, false, creatOcean1);
		assertEquals(creatOcean1.ships[3][5], exampleShip2);
		assertEquals(creatOcean1.ships[4][5], exampleShip2);
		assertEquals(creatOcean1.ships[5][5], exampleShip2);
	}

	@Test
	public void testShootAt() {
		Ocean createOcean2 = new Ocean();
		exampleShip1.placeShipAt(1, 1, true, createOcean2);
		assertTrue(exampleShip1.shootAt(1, 2));
		assertTrue(exampleShip1.shootAt(1, 4));
		assertFalse(exampleShip1.shootAt(1, 5));
		Ocean createOcean = new Ocean();
		exampleShip1.placeShipAt(2, 2, false, createOcean);
		assertTrue(exampleShip1.shootAt(2, 3));
	}

	@Test
	public void testIsSunk() {
		Ocean createOcean2 = new Ocean();
		exampleShip3.placeShipAt(2, 3, true, createOcean2);
		assertFalse(exampleShip3.isSunk());
		exampleShip3.shootAt(2, 3);
		exampleShip3.shootAt(2, 4);
		assertTrue(exampleShip3.isSunk());
		exampleShip2.placeShipAt(5, 6, false, createOcean2);
		assertFalse(exampleShip2.isSunk());
		exampleShip2.shootAt(5, 6);
		exampleShip2.shootAt(6, 6);
		exampleShip2.shootAt(7, 6);
		assertTrue(exampleShip2.isSunk());
	}

	@Test
	public void testToString() {
		Ocean createOcean2 = new Ocean();
		exampleShip3.placeShipAt(1, 1, true, createOcean2);
		assertEquals(createOcean2.ships[1][1].toString(), "S");
		assertEquals(createOcean2.ships[1][2].toString(), "S");
		assertEquals(exampleShip3.toString(), "S");
		exampleShip3.shootAt(1, 1);
		exampleShip3.shootAt(1, 2);
		assertEquals(createOcean2.ships[1][1].toString(), "x");
		assertEquals(createOcean2.ships[1][2].toString(), "x");
		assertEquals(exampleShip3.toString(), "x");
	}

	// The section below is for subclasses of 4 ships
	@Test
	public void testBattleship() {
		Ocean createOcean2 = new Ocean();
		assertEquals(exampleShip1.getShipType(), "battleship");
		assertEquals(exampleShip1.getLength(), 4);
		assertEquals(exampleShip1.hit[0], false);
		assertEquals(exampleShip1.hit[2], false);
		exampleShip1.placeShipAt(1, 1, true, createOcean2);
		exampleShip1.shootAt(1, 1);
		exampleShip1.shootAt(1, 2);
		exampleShip1.shootAt(1, 3);
		exampleShip1.shootAt(1, 4);
		assertEquals(exampleShip1.hit[0], true);
		assertEquals(exampleShip1.hit[1], true);
		assertEquals(exampleShip1.hit[2], true);
		assertEquals(exampleShip1.hit[3], true);
	}

	@Test
	public void testCruiser() {
		Ocean createOcean2 = new Ocean();
		assertEquals(exampleShip2.getShipType(), "cruiser");
		assertEquals(exampleShip2.getLength(), 3);
		assertEquals(exampleShip2.hit[0], false);
		assertEquals(exampleShip2.hit[2], false);
		exampleShip2.placeShipAt(1, 1, true, createOcean2);
		exampleShip2.shootAt(1, 1);
		exampleShip2.shootAt(1, 2);
		exampleShip2.shootAt(1, 3);
		exampleShip2.shootAt(1, 4);
		assertEquals(exampleShip2.hit[0], true);
		assertEquals(exampleShip2.hit[1], true);
		assertEquals(exampleShip2.hit[2], true);
		assertEquals(exampleShip2.hit[3], false);
	}

	@Test
	public void testDestroyer() {
		Ocean createOcean2 = new Ocean();
		assertEquals(exampleShip3.getShipType(), "destroyer");
		assertEquals(exampleShip3.getLength(), 2);
		assertEquals(exampleShip3.hit[0], false);
		assertEquals(exampleShip3.hit[2], false);
		exampleShip3.placeShipAt(1, 1, true, createOcean2);
		exampleShip3.shootAt(1, 1);
		exampleShip3.shootAt(1, 2);
		exampleShip3.shootAt(1, 3);
		exampleShip3.shootAt(1, 4);
		assertEquals(exampleShip3.hit[0], true);
		assertEquals(exampleShip3.hit[1], true);
		assertEquals(exampleShip3.hit[2], false);
		assertEquals(exampleShip3.hit[3], false);
	}

	@Test
	public void testSubmarine() {
		Ocean createOcean2 = new Ocean();
		assertEquals(exampleShip4.getShipType(), "submarine");
		assertEquals(exampleShip4.getLength(), 1);
		assertEquals(exampleShip4.hit[0], false);
		assertEquals(exampleShip4.hit[2], false);
		exampleShip4.placeShipAt(1, 1, true, createOcean2);
		exampleShip4.shootAt(1, 1);
		exampleShip4.shootAt(1, 2);
		exampleShip4.shootAt(1, 3);
		exampleShip4.shootAt(1, 4);
		assertEquals(exampleShip4.hit[0], true);
		assertEquals(exampleShip4.hit[1], false);
		assertEquals(exampleShip4.hit[2], false);
		assertEquals(exampleShip4.hit[3], false);
	}

	// Below section if subclass of EmptySea
	@Test
	public void testEmptySea() {
		Ocean createOcean2 = new Ocean();
		assertEquals(exampleShip5.getShipType(), "empty sea");
		assertEquals(exampleShip5.getLength(), 1);
		assertEquals(exampleShip5.toString(), "-");
		exampleShip5.placeShipAt(1, 1, true, createOcean2);
		exampleShip5.shootAt(1, 1);
		assertEquals(createOcean2.ships[1][1].toString(), "-");
		assertEquals(exampleShip5.hit[0], false);
		exampleShip5.placeShipAt(1, 1, true, createOcean2);
		assertFalse(exampleShip5.isSunk());
		exampleShip5.shootAt(1, 1);
		assertEquals(exampleShip5.shootAt(1, 1), false);
		assertEquals(exampleShip5.hit[0], false);
		assertFalse(exampleShip5.isSunk());

	}

}
