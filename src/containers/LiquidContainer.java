
package containers;


public class LiquidContainer extends HeavyContainer {

	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
	
	}

	@Override
	public double consumption() {
		System.out.println("Fuel Consumption by Liquid Container"+" "+this.ID+" = "+4.00 * this.weight);
		return 4.00 * this.weight;
	}	
}


