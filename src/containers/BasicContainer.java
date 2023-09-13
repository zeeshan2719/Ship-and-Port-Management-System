
package containers;

public class BasicContainer extends Container {

	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	}

	@Override
	public double consumption() {
		System.out.println("Fuel Consumption by Basic Container"+" "+this.ID+" = "+2.50 * this.weight);
		return 2.50 * this.weight;
	}

	@Override
	public boolean equals(Container other) {
		if (this.getClass() == other.getClass() && this.ID == other.ID && this.weight == other.weight) {
			return true;
		}
		else {
			return false;
		}
	}

}
