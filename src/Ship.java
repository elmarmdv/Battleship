
public abstract class Ship {

	protected int bowColumn;
	protected int bowRow;
	protected boolean[] hit;
	protected boolean horizontal;
	protected int length;

	public int getLength() {
		return length;
	}

	public int getBowRow() {
		return bowRow;
	}

	public int getBowColumn() {
		return bowColumn;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public abstract String getShipType();

	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		// if horizontal
		if (horizontal) {
			if (10 - column < length) {
				// BAD
				return false;
			} else {
				// see if the perimeter where we want to place a ship is vacant

				int leftBound = (row == 0) ? row : row - 1;
				int rightBound = (row == 9) ? row : row + 1;
				int upperBound = (column == 0) ? column : column - 1;
				int lowerBound = (column + length - 1 == 9) ? column + length - 1 : column + length;

				for (int i = leftBound; i <= rightBound; i++) {
					for (int j = upperBound; j <= lowerBound; j++) {
						if (!(ocean.ships[i][j].getShipType().equals("empty"))) {
							return false;
						}
					}
				}
			}
			// if vertical
		} else {

			if (10 - row < length) {
				return false;
			} else {
				// see if the perimeter where we want to place a ship is vacant
				int leftBound = (row == 0) ? row : row - 1;
				int rightBound = (row + length - 1 == 9) ? row + length - 1 : row + length;
				int upperBound = (column == 0) ? column : column - 1;
				int lowerBound = (column == 9) ? column : column + 1;

				for (int i = leftBound; i <= rightBound; i++) {
					for (int j = upperBound; j <= lowerBound; j++) {
						if (!(ocean.ships[i][j].getShipType().equals("empty"))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		setBowRow(row);
		setBowColumn(column);
		setHorizontal(horizontal);
		// place the ship in the ocean
		if (horizontal) { // if horizontal
			for (int i = column; i <= column + length - 1; i++) {
				ocean.ships[row][i] = this;
			}
		} else { // if vertical
			for (int i = row; i <= row + length - 1; i++) {
				ocean.ships[i][column] = this;
			}
		}
	}

	public boolean shootAt(int row, int column) {
		// calculate which tile is being shot
		int tileShot = (row - bowRow) + (column - bowColumn);
		if (!this.isSunk()) {
			if (!this.hit[tileShot]) {

				this.hit[tileShot] = true;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean tileBeenHit(int tile) {
		return hit[tile];
	}

	public boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (hit[i] == false) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}

}
