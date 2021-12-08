
public class Submarine extends Ship {
	public Submarine() {
		length = 1;
		tilesHit = new boolean[length];
	}

	@Override
	public String getShipType() {
		return "submarine";
	}
}
