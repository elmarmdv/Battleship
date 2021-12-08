import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class OceanTest {
	private Ocean o;

	// ---------- TESTING placeShip() ---------- //

	@Test
	public void placeBattleshipAt4x2ver() {
		o = new Ocean();
		int[] coordinates = { 4, 2, 1 };
		Battleship bs = new Battleship();
		o.placeShip(bs, coordinates);

		Ship[][] expectedShips = new Ship[10][10];

		expectedShips[4][2] = bs;
		expectedShips[4][3] = bs;
		expectedShips[4][4] = bs;
		expectedShips[4][5] = bs;

		assertEquals(expectedShips[4][2], o.getShipArray()[4][2]);
		assertEquals(expectedShips[4][2], o.getShipArray()[4][3]);
		assertEquals(expectedShips[4][2], o.getShipArray()[4][4]);
		assertEquals(expectedShips[4][2], o.getShipArray()[4][5]);
	}

	@Test
	public void placeBattleshipAt0x0hor() {
		o = new Ocean();
		int[] coordinates = { 0, 0, 0 };
		Cruiser cr = new Cruiser();
		o.placeShip(cr, coordinates);

		Ship[][] expectedShips = new Ship[10][10];

		expectedShips[0][0] = cr;
		expectedShips[1][0] = cr;
		expectedShips[2][0] = cr;

		assertEquals(expectedShips[0][0], o.getShipArray()[0][0]);
		assertEquals(expectedShips[1][0], o.getShipArray()[1][0]);
		assertEquals(expectedShips[2][0], o.getShipArray()[2][0]);
	}

	// ---------- TESTING shipFits() ---------- //

	@Test
	public void doesShipFitLenth1X9Y9hor() {
		o = new Ocean();
		int[] coordinates = { 9, 9, 0 };
		assertTrue(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X9Y9ver() {
		o = new Ocean();
		int[] coordinates = { 9, 9, 1 };
		assertTrue(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth2X9Y9hor() {
		o = new Ocean();
		int[] coordinates = { 9, 9, 0 };
		assertFalse(o.shipFits(2, coordinates));
	}

	@Test
	public void doesShipFitLenth2X9Y9ver() {
		o = new Ocean();
		int[] coordinates = { 9, 9, 1 };
		assertFalse(o.shipFits(2, coordinates));
	}

	@Test
	public void doesShipFitLenth3X7Y9hor() {
		o = new Ocean();
		int[] coordinates = { 7, 9, 0 };
		assertTrue(o.shipFits(3, coordinates));
	}

	@Test
	public void doesShipFitLenth4X7Y9hor() {
		o = new Ocean();
		int[] coordinates = { 7, 9, 0 };
		assertFalse(o.shipFits(4, coordinates));
	}

	@Test
	public void doesShipFitLenth2X9Y8ver() {
		o = new Ocean();
		int[] coordinates = { 9, 8, 1 };
		assertTrue(o.shipFits(2, coordinates));
	}

	@Test
	public void doesShipFitLenth3X9Y8ver() {
		o = new Ocean();
		int[] coordinates = { 9, 8, 1 };
		assertFalse(o.shipFits(3, coordinates));
	}

	@Test
	public void doesShipFitLenth4X0Y0hor() {
		o = new Ocean();
		int[] coordinates = { 0, 0, 0 };
		assertTrue(o.shipFits(4, coordinates));
	}

	@Test
	public void doesShipFitLenth4X0Y0ver() {
		o = new Ocean();
		int[] coordinates = { 0, 0, 1 };
		assertTrue(o.shipFits(4, coordinates));
	}

	// Test when there is another ship in place/nearby

	@Test
	public void doesShipFitLenth1X5Y5horOccupied5x5() {
		o = new Ocean();
		o.setShip(new Submarine(), 5, 5);
		int[] coordinates = { 5, 5, 0 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5horOccupied5x6() {
		o = new Ocean();
		o.setShip(new Submarine(), 5, 6);
		int[] coordinates = { 5, 5, 0 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5horOccupied6x5() {
		o = new Ocean();
		o.setShip(new Submarine(), 6, 5);
		int[] coordinates = { 5, 5, 0 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5horOccupied6x6() {
		o = new Ocean();
		o.setShip(new Submarine(), 6, 6);
		int[] coordinates = { 5, 5, 0 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5horOccupied6x7() {
		o = new Ocean();
		o.setShip(new Submarine(), 6, 7);
		int[] coordinates = { 5, 5, 0 };
		assertTrue(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5verOccupied5x5() {
		o = new Ocean();
		o.setShip(new Submarine(), 5, 5);
		int[] coordinates = { 5, 5, 1 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5verOccupied5x4() {
		o = new Ocean();
		o.setShip(new Submarine(), 5, 4);
		int[] coordinates = { 5, 5, 1 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5verOccupied4x5() {
		o = new Ocean();
		o.setShip(new Submarine(), 4, 5);
		int[] coordinates = { 5, 5, 1 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5verOccupied4x4() {
		o = new Ocean();
		o.setShip(new Submarine(), 4, 4);
		int[] coordinates = { 5, 5, 1 };
		assertFalse(o.shipFits(1, coordinates));
	}

	@Test
	public void doesShipFitLenth1X5Y5verOccupied4x3() {
		o = new Ocean();
		o.setShip(new Submarine(), 4, 3);
		int[] coordinates = { 5, 5, 1 };
		assertTrue(o.shipFits(1, coordinates));
	}

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
