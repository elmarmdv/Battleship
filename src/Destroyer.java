
public class Destroyer extends Ship {
	public Destroyer() {
		length = 2;
		hit = new boolean[length];
		for (int i = 0; i < length; i++) {
			hit[i] = false;
		}
	}

	@Override
	public String getShipType() {
		return "Destroyer";
	}
}
