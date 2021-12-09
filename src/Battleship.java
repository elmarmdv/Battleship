
public class Battleship extends Ship {
	public Battleship() {
		length = 4;
		hit = new boolean[length];
		for (int i = 0; i < length; i++) {
			hit[i] = false;
		}
	}

	@Override
	public String getShipType() {
		return "Battleship";
	}
}
