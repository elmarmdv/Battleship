
public class EmptySea extends Ship {

	public EmptySea() {
		this.length = 1;
		this.hit = new boolean[length];
		for (int i = 0; i < length; i++) {
			hit[i] = false;
		}
	}

	@Override
	public boolean shootAt(int row, int column) {
		this.hit[0] = true;
		return false;
	}

	@Override
	public boolean isSunk() {
		return false;
	}

	@Override
	public String toString() {
		if (this.hit[0]) {
			return "-";
		} else {
			return ".";
		}
	}

	@Override
	public String getShipType() {
		return "empty";
	}
}
