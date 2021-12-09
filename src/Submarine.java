
public class Submarine extends Ship {
	public Submarine() {
		length = 1;
		hit = new boolean[length];
		for (int i = 0; i < length; i++) {
			hit[i] = false;
		}
	}

	@Override
	public String getShipType() {
		return "Submarine";
	}
}
