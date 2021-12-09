import java.util.Random;

/**
 * This class manages the game state by keeping track of what entity is
 * contained in each position on the game board.
 * 
 * @author harry
 *
 */
public class Ocean implements OceanInterface {

	/**
	 * A 10x10 2D array of Ships, which can be used to quickly determine which ship
	 * is in any given location.
	 */
	protected Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	protected int shotsFired;

	/**
	 * The number of times a shot hit a ship. If the user shoots the same part of a
	 * ship more than once, every hit is counted, even though the additional "hits"
	 * don't do the user any good.
	 */
	protected int hitCount;

	/**
	 * The number of ships totally sunk.
	 * 
	 */
	protected int shipsSunk;

	/**
	 * Creates an "empty" ocean, filling every space in the <code>ships</code> array
	 * with EmptySea objects. Should also initialize the other instance variables
	 * appropriately.
	 */
	public Ocean() {

		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;

		ships = new Ship[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
			}
		}
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Larger ships
	 * must be placed before smaller ones to avoid cases where it may be impossible
	 * to place the larger ships.
	 * 
	 * @see java.util.Random
	 */
	public void placeAllShipsRandomly() {

		Battleship[] battleships = new Battleship[1];
		Cruiser[] cruisers = new Cruiser[2];
		Destroyer[] destroyers = new Destroyer[3];
		Submarine[] submarines = new Submarine[4];

		for (int i = 0; i < battleships.length; i++) {
			battleships[i] = new Battleship();
			attemptPlacement(battleships[i]);
		}

		for (int i = 0; i < cruisers.length; i++) {
			cruisers[i] = new Cruiser();
			attemptPlacement(cruisers[i]);
		}

		for (int i = 0; i < destroyers.length; i++) {
			destroyers[i] = new Destroyer();
			attemptPlacement(destroyers[i]);
		}

		for (int i = 0; i < submarines.length; i++) {
			submarines[i] = new Submarine();
			attemptPlacement(submarines[i]);
		}
	}

	private boolean attemptPlacement(Ship ship) {
		boolean successfullyPlaced = false;
		while (!successfullyPlaced) {
			int[] coordinates = generateRandomPlacement();
			if (ship.okToPlaceShipAt(coordinates[0], coordinates[1], coordinates[2] == 0, this)) {
				ship.placeShipAt(coordinates[0], coordinates[1], coordinates[2] == 0, this);
				successfullyPlaced = true;
			}
		}
		return true;
	}

	private int[] generateRandomPlacement() {
		int[] randomValues = new int[3];
		// generate random values for placement
		Random rndm = new Random();
		randomValues[0] = rndm.nextInt(10); // x coordinate
		randomValues[1] = rndm.nextInt(10); // y coordinate
		randomValues[2] = rndm.nextInt(2); // 0 for horizontal, 1 for vertical placement

		return randomValues;
	}

	public boolean shipFits(int shipLength, int[] coordinates) {
		int x = coordinates[0];
		int y = coordinates[1];
		String orientation = (coordinates[2] == 0) ? "horizontal" : "vertical";

		// if horizontal
		if (orientation.equals("horizontal")) {
			if (10 - x < shipLength) {
				// BAD
				return false;
			} else {
				// see if the perimeter where we want to place a ship is vacant
				int leftBound = (x == 0) ? x : x - 1;
				int rightBound = (x + shipLength - 1 == 9) ? x + shipLength - 1 : x + shipLength;
				int upperBound = (y == 0) ? y : y - 1;
				int lowerBound = (y == 9) ? y : y + 1;

				for (int i = leftBound; i <= rightBound; i++) {
					for (int j = upperBound; j <= lowerBound; j++) {
						if (!(ships[i][j] instanceof EmptySea)) {
							return false;
						}
					}
				}
			}
			// if vertical
		} else {
			if (10 - y < shipLength) {
				return false;
			} else {
				// see if the perimeter where we want to place a ship is vacant
				int leftBound = (x == 0) ? x : x - 1;
				int rightBound = (x == 9) ? x : x + 1;
				int upperBound = (y == 0) ? y : y - 1;
				int lowerBound = (y + shipLength - 1 == 9) ? y + shipLength - 1 : y + shipLength;

				for (int i = leftBound; i <= rightBound; i++) {
					for (int j = upperBound; j <= lowerBound; j++) {
						if (!(ships[i][j] instanceof EmptySea)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks if this coordinate is not empty; that is, if this coordinate does not
	 * contain an EmptySea reference.
	 * 
	 * @param row    the row (0 to 9) in which to check for a floating ship
	 * @param column the column (0 to 9) in which to check for a floating ship
	 * @return {@literal true} if the given location contains a ship, and
	 *         {@literal false} otherwise.
	 */
	public boolean isOccupied(int row, int column) {
		boolean occupied = (ships[row][column].getShipType().equals("empty")) ? false : true;
		return occupied;
	}

	/**
	 * Fires a shot at this coordinate. This will up date the number of shots that
	 * have been fired (and potentially the number of hits, as well). If a location
	 * contains a real, not sunk ship, this method should return {@literal true}
	 * every time the user shoots at that location. If the ship has been sunk,
	 * additional shots at this location should return {@literal false}.
	 * 
	 * @param row    the row (0 to 9) in which to shoot
	 * @param column the column (0 to 9) in which to shoot
	 * @return {@literal true} if the given location contains an afloat ship (not an
	 *         EmptySea), {@literal false} if it does not.
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		Ship shipShot = ships[row][column];
		boolean hitInflicted = shipShot.shootAt(row, column);
		if (hitInflicted) {
			hitCount++;
			System.out.println("hit");
			if (shipShot.isSunk()) {
				shipsSunk++;
			}
			return true;
		} else {
			System.out.println("miss");
			return false;
		}
	}

	/**
	 * @return the number of shots fired in this game.
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * @return the number of hits recorded in this game.
	 */
	public int getHitCount() {
		return this.shotsFired;
	}

	/**
	 * @return the number of ships sunk in this game.
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * @return {@literal true} if all ships have been sunk, otherwise
	 *         {@literal false}.
	 */
	public boolean isGameOver() {
		if (getShipsSunk() == 10) {
			return true;
		}
		return false;
	}

	/**
	 * Provides access to the grid of ships in this Ocean. The methods in the Ship
	 * class that take an Ocean parameter must be able to read and even modify the
	 * contents of this array. While it is generally undesirable to allow methods in
	 * one class to directly access instancce variables in another class, in this
	 * case there is no clear and elegant alternatives.
	 * 
	 * @return the 10x10 array of ships.
	 */
	public Ship[][] getShipArray() {
		return ships;
	}

	// sets a custom ship at a location (for testing purposes)
	public void setShip(Ship ship, int x, int y) {
		ships[x][y] = ship;
	}

	/**
	 * Prints the ocean. To aid the user, row numbers should be displayed along the
	 * left edge of the array, and column numbers should be displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10. The top left corner square should be
	 * 0, 0.
	 * <ul>
	 * <li>Use 'S' to indicate a location that you have fired upon and hit a (real)
	 * ship</li>
	 * <li>'-' to indicate a location that you have fired upon and found nothing
	 * there</li>
	 * <li>'x' to indicate a location containing a sunken ship</li>
	 * <li>'.' (a period) to indicate a location that you have never fired
	 * upon.</li>
	 * </ul>
	 * 
	 * This is the only method in Ocean that has any printing capability, and it
	 * should never be called from within the Ocean class except for the purposes of
	 * debugging.
	 * 
	 */
	public void print() {
		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].toString().equals("S")) {
					int tileShot = (i - ships[i][j].getBowRow()) + (j - ships[i][j].getBowColumn());
					if (ships[i][j].tileBeenHit(tileShot)) {
						System.out.print("S");
					} else {
						System.out.print(".");
					}
				} else {
					System.out.print(ships[i][j].toString());
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
