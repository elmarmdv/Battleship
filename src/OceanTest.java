import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class OceanTest {
	private Ocean o;

	// ---------- TESTING placeAllShipsRandomly() ---------- //

	@Test
	public void testRandomShipPlacement() {

		// Run random placement and check for correctly spaced ships 10 times
		for (int i = 0; i < 10; i++) {
			Ocean oc = new Ocean();
			oc.placeAllShipsRandomly();
			// check the whole ocean for incorrectly placed ships
			boolean allCorrect = testRandomShipPlacementHelper(oc);
			assertTrue(allCorrect);
		}
	}

	// helper method for testing random ship placement correctness
	public boolean testRandomShipPlacementHelper(Ocean oc) {
		boolean allShipsPlacedCorrectly = true;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship ship = oc.getShipArray()[i][j];
				if (!ship.getShipType().equals("empty")) {
					for (int m = i - 1; m <= i + 1; m++) {
						for (int n = j - 1; n <= j + 1; n++) {
							int column;
							int row;
							if (m < 0) {
								row = 0;
							} else if (m > 9) {
								row = 9;
							} else {
								row = m;
							}
							if (n < 0) {
								column = 0;
							} else if (n > 9) {
								column = 9;
							} else {
								column = n;
							}
							if (!oc.ships[row][column].getShipType().equals("empty")
									&& !oc.ships[row][column].equals(ship)) {
								allShipsPlacedCorrectly = false;
							}
						}
					}
				}
			}
		}
		return allShipsPlacedCorrectly;
	}

	@Test
	// Test that correct number of each type of ship has been placed
	public void testAllShipsArePlaced() {
		Ocean oc = new Ocean();
		oc.placeAllShipsRandomly();
		HashMap<String, HashSet<Ship>> shipCounter = new HashMap<String, HashSet<Ship>>();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship ship = oc.getShipArray()[i][j];
				if (shipCounter.containsKey(ship.getShipType())) {
					shipCounter.get(ship.getShipType()).add(ship);
				} else {
					shipCounter.put(ship.getShipType(), new HashSet<Ship>(Arrays.asList(ship)));
				}
			}
		}
		assertEquals(1, shipCounter.get("Battleship").size());
		assertEquals(2, shipCounter.get("Cruiser").size());
		assertEquals(3, shipCounter.get("Destroyer").size());
		assertEquals(4, shipCounter.get("Submarine").size());
	}

	// ---------- TESTING isOccupied() ---------- //

	@Test
	public void isOccupiedWhenOccupied() {
		Ocean oc = new Ocean();
		Submarine sm = new Submarine();
		sm.placeShipAt(4, 4, true, oc);
		assertTrue(oc.isOccupied(4, 4));
	}

	@Test
	public void isOccupiedWhenVacant() {
		Ocean oc = new Ocean();
		Submarine sm = new Submarine();
		sm.placeShipAt(4, 4, true, oc);
		assertFalse(oc.isOccupied(3, 4));
	}

	// ---------- TESTING shootAt() and getters ---------- //

	@Test
	public void shootAtEmptySea() {
		Ocean oc = new Ocean();
		assertEquals(0, oc.getShotsFired());
		assertEquals(0, oc.getHitCount());
		assertFalse(oc.shootAt(3, 3));
		assertEquals(1, oc.getShotsFired());
		assertEquals(0, oc.getShipsSunk());
		assertEquals(0, oc.getHitCount());
	}

	@Test
	public void shootAtUnsunkDestroyer() {
		Ocean oc = new Ocean();
		Destroyer ds = new Destroyer();
		ds.placeShipAt(4, 4, true, oc);

		assertEquals(0, oc.getShotsFired());
		assertEquals(0, oc.getHitCount());
		// shoot once, but not sink
		assertTrue(oc.shootAt(4, 4));
		assertEquals(1, oc.getShotsFired());
		assertEquals(0, oc.getShipsSunk());
		assertEquals(1, oc.getHitCount());
		// shoot same place twice
		assertTrue(oc.shootAt(4, 4));
		assertEquals(2, oc.getShotsFired());
		assertEquals(0, oc.getShipsSunk());
		assertEquals(2, oc.getHitCount());
	}

	@Test
	public void shootAtAndSinkSubmarine() {
		Ocean oc = new Ocean();
		Submarine sm = new Submarine();
		sm.placeShipAt(4, 4, true, oc);
		// shoot once before sinking

		assertEquals(0, oc.getShotsFired());
		assertEquals(0, oc.getHitCount());
		assertTrue(oc.shootAt(4, 4));
		assertEquals(1, oc.getShotsFired());
		assertEquals(1, oc.getShipsSunk());
		assertEquals(1, oc.getHitCount());
		// shoot again after sinking
		assertFalse(oc.shootAt(4, 4));
		assertEquals(2, oc.getShotsFired());
		assertEquals(1, oc.getShipsSunk());
		assertEquals(1, oc.getHitCount());
	}

	// ---------- TESTING isGameOver() ---------- //

	@Test
	public void isGameOverWhenOver() {
		Ocean oc = new Ocean();
		oc.placeAllShipsRandomly();

		assertFalse(oc.isGameOver());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!oc.getShipArray()[i][j].getShipType().equals("empty")) {
					oc.shootAt(i, j);
					if (oc.getShipsSunk() < 10) {
						assertFalse(oc.isGameOver());
					} else {
						assertTrue(oc.isGameOver());
					}
				}
			}
		}
	}

	// PRINT OCEAN FOR DEBUGGING, DELETE LATER!

	@Test
	public void printOcean() {
		o = new Ocean();
		o.placeAllShipsRandomly();
		o.print();
	}

}
