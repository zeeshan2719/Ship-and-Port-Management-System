
package containers;

public class RefrigeratedContainer extends HeavyContainer {
	
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
	
	}

	@Override
	public double consumption() {
		System.out.println("Fuel Consumption by Refrigerated Container"+" "+this.ID+" = "+5.00 * this.weight);
		return 5.00 * this.weight;
	}	
}