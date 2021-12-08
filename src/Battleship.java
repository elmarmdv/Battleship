
public class Battleship extends Ship {
	public Battleship() {
		length = 4;
		tilesHit = new boolean[length];
	}

	@Override
	public String getShipType() {
		return "battleship";
	}
}
