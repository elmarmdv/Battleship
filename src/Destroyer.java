
public class Destroyer extends Ship {
	public Destroyer() {
		length = 2;
		tilesHit = new boolean[length];
	}

	@Override
	public String getShipType() {
		return "destroyer";
	}
}
