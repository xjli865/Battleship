package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean theOcean;

	@Before
	public void Setup() {
		theOcean = new Ocean();
	}

	@Test
	public void testShotsFired() {
		assertEquals(theOcean.getShotsFired(), theOcean.shotsFired);
		assertEquals(theOcean.shotsFired, 0);
	}

	@Test
	public void testHitCount() {
		assertEquals(theOcean.getHitCount(), theOcean.hitCount);
		assertEquals(theOcean.hitCount, 0);
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		int countShips = 0;
		int countBattleship = 0;
		int countCruiser = 0;
		int countDestroyer = 0;
		int countSubmarine = 0;
		theOcean.placeAllShipsRandomly();
		for (int i = 0; i < theOcean.ships.length; i++) {
			for (int j = 0; j < theOcean.ships.length; j++) {
				if (!(theOcean.ships[i][j] instanceof EmptySea)) {
					countShips += 1;
					if (theOcean.ships[i][j] instanceof Battleship) {
						countBattleship += 1;
					} else if (theOcean.ships[i][j] instanceof Cruiser) {
						countCruiser += 1;
					} else if (theOcean.ships[i][j] instanceof Destroyer) {
						countDestroyer += 1;
					} else if (theOcean.ships[i][j] instanceof Submarine) {
						countSubmarine += 1;
					}
				}
			}
		}
		assertEquals(countShips, 20);
		assertEquals(countBattleship, 4);
		assertEquals(countCruiser, 6);
		assertEquals(countDestroyer, 6);
		assertEquals(countSubmarine, 4);
	}

	@Test
	public void testIsOccupied() {
		Cruiser myship = new Cruiser();
		assertFalse(theOcean.isOccupied(1, 1));
		myship.placeShipAt(1, 1, true, theOcean);
		assertTrue(theOcean.isOccupied(1, 1));
		assertTrue(theOcean.isOccupied(1, 2));
		assertTrue(theOcean.isOccupied(1, 3));
		assertFalse(theOcean.isOccupied(3, 3));
	}

	@Test
	public void testShootAt() {
		Cruiser myship = new Cruiser();
		assertFalse(theOcean.shootAt(1, 1));
		myship.placeShipAt(1, 1, true, theOcean);
		assertTrue(theOcean.shootAt(1, 1));
		assertTrue(theOcean.shootAt(1, 2));
		assertFalse(theOcean.shootAt(1, 3));
		assertFalse(theOcean.shootAt(1, 1));
		assertFalse(theOcean.shootAt(1, 2));
	}

	@Test
	public void testGetShotsFired() {
		Cruiser myship = new Cruiser();
		myship.placeShipAt(2, 2, false, theOcean);
		theOcean.shootAt(2, 2);
		theOcean.shootAt(3, 3);
		theOcean.shootAt(4, 4);
		assertEquals(theOcean.getShotsFired(), 3);
	}

	@Test
	public void testGetHitCount() {
		Cruiser myship = new Cruiser();
		myship.placeShipAt(2, 2, false, theOcean);
		theOcean.shootAt(2, 2);
		theOcean.shootAt(2, 2);
		theOcean.shootAt(3, 2);
		assertEquals(theOcean.getShotsFired(), 3);
		theOcean.shootAt(4, 2);
		theOcean.shootAt(2, 2);
		assertEquals(theOcean.getShotsFired(), 5);
	}

	@Test
	public void testIsGameOver() {
		Ship ship1 = new Battleship();
		ship1.placeShipAt(8, 5, true, theOcean);
		theOcean.tenShips[0] = ship1;
		Ship ship2 = new Cruiser();
		ship2.placeShipAt(0, 0, true, theOcean);
		theOcean.tenShips[1] = ship2;
		Ship ship3 = new Cruiser();
		ship3.placeShipAt(2, 2, true, theOcean);
		theOcean.tenShips[2] = ship3;
		Ship ship4 = new Destroyer();
		ship4.placeShipAt(0, 6, false, theOcean);
		theOcean.tenShips[3] = ship4;
		Ship ship5 = new Destroyer();
		ship5.placeShipAt(3, 9, false, theOcean);
		theOcean.tenShips[4] = ship5;
		Ship ship6 = new Destroyer();
		ship6.placeShipAt(6, 1, true, theOcean);
		theOcean.tenShips[5] = ship6;
		Ship ship7 = new Submarine();
		ship7.placeShipAt(3, 0, false, theOcean);
		theOcean.tenShips[6] = ship7;
		Ship ship8 = new Submarine();
		ship8.placeShipAt(5, 5, false, theOcean);
		theOcean.tenShips[7] = ship8;
		Ship ship9 = new Submarine();
		ship9.placeShipAt(4, 7, false, theOcean);
		theOcean.tenShips[8] = ship9;
		Ship ship10 = new Submarine();
		ship10.placeShipAt(9, 2, false, theOcean);
		theOcean.tenShips[9] = ship10;
		assertFalse(theOcean.isGameOver());
		theOcean.shootAt(0, 1);
		assertFalse(theOcean.isGameOver());
		theOcean.shootAt(0, 0);
		theOcean.shootAt(0, 2);
		assertFalse(theOcean.isGameOver());
		theOcean.shootAt(0, 6);
		theOcean.shootAt(1, 6);
		theOcean.shootAt(2, 2);
		theOcean.shootAt(2, 3);
		theOcean.shootAt(2, 4);
		theOcean.shootAt(3, 0);
		theOcean.shootAt(3, 9);
		theOcean.shootAt(4, 7);
		theOcean.shootAt(4, 9);
		theOcean.shootAt(5, 5);
		assertFalse(theOcean.isGameOver());
		theOcean.shootAt(6, 1);
		theOcean.shootAt(6, 2);
		theOcean.shootAt(8, 5);
		theOcean.shootAt(8, 6);
		theOcean.shootAt(8, 7);
		theOcean.shootAt(8, 8);
		assertFalse(theOcean.isGameOver());
		theOcean.shootAt(9, 2);
		assertTrue(theOcean.isGameOver());
	}

}
