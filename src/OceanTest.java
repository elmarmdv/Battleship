import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class OceanTest {
	private Ocean o;

//	// ---------- TESTING placeAllShipsRandomly() ---------- //
//
//	@Test
//	public void testRandomShipPlacement() {
//		Ocean oc = new Ocean();
//		oc.placeAllShipsRandomly();
//		for (int i = 0; i < 100; i++) {
//			// check the whole ocean for incorrectly placed ships
//			boolean allCorrect = testRandomShipPlacementHelper(oc);
//			assertTrue(allCorrect);
//		}
//	}
//
//	// helper method for testing random ship placement correctness
//	public boolean testRandomShipPlacementHelper(Ocean oc) {
//		boolean allShipsPlacedCorrectly = true;
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				Ship ship = oc.getShipArray()[i][j];
//				if (!ship.getShipType().equals("empty")) {
//					for (int m = i - 1; m <= i + 1; m++) {
//						for (int n = j - 1; n <= j + 1; n++) {
//							if (!oc.ships[m][n].getShipType().equals("empty")) {
//								allShipsPlacedCorrectly = false;
//							}
//						}
//					}
//				}
//			}
//		}
//		return allShipsPlacedCorrectly;
//	}

	@Test
	public void testAllShipsArePlaced() {
		Ocean oc = new Ocean();
		oc.placeAllShipsRandomly();
		HashMap<String, ArrayList<Ship>> shipCounter = new HashMap<String, ArrayList<Ship>>();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship ship = oc.getShipArray()[i][j];
				if (shipCounter.containsKey(ship.getShipType())) {
					shipCounter.get(ship.getShipType()).add(ship);
				} else {
					shipCounter.put(ship.getShipType(), new ArrayList<Ship>(Arrays.asList(ship)));
				}
			}
		}
		System.out.println("There are battleships " + shipCounter.get("Battleship").size());
		// assertEquals(1, shipCounter.get("Battleship"));

	}

	// ---------- TESTING shipFits() ---------- //

	// ---------- TESTING isOccupied() ---------- //

	@Test
	public void isPlaceOccupiedWhenOccupied() {
		o = new Ocean();
		o.ships[4][4] = new Submarine();
		assertTrue(o.isOccupied(4, 4));
	}

	@Test
	public void isPlaceOccupiedWhenEmpty() {
		o = new Ocean();
		o.ships[4][4] = new Submarine();
		assertFalse(o.isOccupied(3, 4));
	}

	// PRINT OCEAN FOR DEBUGGING, DELETE LATER!

	@Test
	public void printOcean() {
		o = new Ocean();
		o.placeAllShipsRandomly();
		o.print();
	}

}
