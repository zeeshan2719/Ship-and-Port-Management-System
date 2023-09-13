
package containers;

public class HeavyContainer extends Container {
	
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	@Override
	public double consumption() {
		System.out.println("Fuel Consumption by Heavy Container"+" "+this.ID+" = "+3.00 * this.weight);
		return 3.00 * this.weight;
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

