
public class Cruiser extends Ship {
	public Cruiser() {
		length = 3;
		tilesHit = new boolean[length];
	}

	@Override
	public String getShipType() {
		return "cruiser";
	}
}
