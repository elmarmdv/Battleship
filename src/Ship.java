
public abstract class Ship {
	protected int length;
	protected String shipType;
	protected int bowX;
	protected int bowY;
	protected boolean[] tilesHit;
	protected boolean beenShot;

	public void setBowLocation(int x, int y) {
		bowX = x;
		bowY = y;
	}

	public int getLength() {
		return length;
	}

	public String getShipType() {
		return null;
	}

	public int[] getBowLocation() {
		int[] bowLocation = { bowX, bowY };
		return bowLocation;
	}

	public boolean isTileHit(int tileNumber) {
		return tilesHit[tileNumber];
	}

	public void updateHits(int tile) {
		tilesHit[tile] = true;
	}

	public boolean isSunk() {
		for (int i = 0; i < length; i++) {
			if (tilesHit[i] == false) {
				return false;
			}
		}
		return true;
	}

	public void emptyShot() {
		beenShot = true;
	}

	public boolean beenShotAt() {
		return beenShot;
	}
}
