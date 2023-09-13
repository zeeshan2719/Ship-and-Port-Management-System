
package containers;

public abstract class Container {
	
	protected final int ID;
	protected final int weight; 
	public Container(int ID, int weight) {
		
		this.ID = ID;
		this.weight = weight;
	}
	public abstract double consumption();
	public abstract boolean equals(Container other);

	public int getID() {
		return ID;
	}

	public int getWeight() {
		return weight;
	}
	
}
