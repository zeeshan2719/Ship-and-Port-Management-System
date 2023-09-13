
package ships;

import java.util.ArrayList;



import containers.*;
import ports.Port;

interface InterShip {
	
	boolean sailTo(Port p);
	
	void reFuel(double newFuel);
	
	boolean load(Container cont);
	
	boolean unLoad(Container cont);
}

public class Ship implements InterShip {
	
	private final int ID;
	private double fuel;
	private Port currentPort;
	private int totalWeightCapacity;
	private int maxNumberOfAllContainers;
	private int maxNumberOfHeavyContainers;
	private int maxNumberOfRefrigeratedContainers;
	private int maxNumberOfLiquidContainers; 
	private double fuelConsumptionPerKM;
	//Keeps track of the containers in the ship.
	ArrayList<Container> currentContainers = new ArrayList<Container>();
	//Constructor
	public Ship(int ID, Port p, int totalWeightCapacity, int
			maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers, int
			maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		
		this.fuel = 0;
		this.ID = ID;
		this.setCurrentPort(p);
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		
	}
	
	private int numberOfContainers = 0;
	private int numberOfHeavyContainers = 0;
	private int numberOfLiquidContainers = 0;
	private int numberOfRefrigeratedContainers = 0;
	private int weightOfContainers = 0;
	
	//Sorts Containers in the Ship by their ID. 
	public ArrayList<Container> getCurrentContainers() {
		
		ArrayList<Container> sortedContainers = new ArrayList<Container>();
		sortedContainers.addAll(currentContainers);
	   
		int length = sortedContainers.size();
		
		for (int i = 0; i < length; i++) {
	        for (int j = i + 1; j < length; j++) {
	            Container temp;
	            if (sortedContainers.get(i).getID() > sortedContainers.get(j).getID()) {
	                temp = sortedContainers.get(i);
	                sortedContainers.remove(i);
	                sortedContainers.add(i, sortedContainers.get(j-1));
	                sortedContainers.remove(j);
	                sortedContainers.add(j, temp);
	            }
	        }
	    }
	return sortedContainers;		
	}
	
	//Sails the ship to the given port.
	@Override
	public boolean sailTo(Port p) {
		
		double containerFuelConsumptionPerKM = 0;
		for (Container c : currentContainers) {
			containerFuelConsumptionPerKM += c.consumption();
		}
		//Add both ship consumption and container consumption 
		double totalFuelConsumption = containerFuelConsumptionPerKM + this.fuelConsumptionPerKM;
		System.out.println("Fuel Consuption by the Ship "+this.ID+" = "+this.fuelConsumptionPerKM);
		double requiredFuel = this.getCurrentPort().getDistance(p) * totalFuelConsumption;
//		System.out.println("Fuel Consuption by the Ship "+this.ID+" = "+this.fuelConsumptionPerKM);
		System.out.println("Required Fuel to sail the Ship = "+requiredFuel);
		System.out.println("Fuel you have = "+this.fuel);
		
		if (requiredFuel <= this.fuel) {		
			this.fuel -= requiredFuel;
			this.getCurrentPort().outgoingShip(this);
			this.setCurrentPort(p);
			this.getCurrentPort().incomingShip(this);
			return true;
		}
		
		else {
			System.out.println("...Ship has less fuel...");
			return false;
		}
	}

	//Refuels the ship.
	@Override
	public void reFuel(double newFuel) {
		System.out.println("...Re-Fuel done...");
		System.out.println("Total Fuel Added = "+newFuel);
		
		this.fuel += newFuel;
		
	}
	
	//Loads the container to the ship if the requirements are fulfilled.
	@Override
	public boolean load(Container cont) {
		
		if (this.getCurrentPort().containers.contains(cont) == true) {
			
				if (cont instanceof RefrigeratedContainer == true) {
					if (this.weightOfContainers + cont.getWeight() <= this.totalWeightCapacity
							&& this.numberOfRefrigeratedContainers + 1 <= this.maxNumberOfRefrigeratedContainers
							&& this.numberOfHeavyContainers + 1 <= this.maxNumberOfHeavyContainers
							&& this.numberOfContainers + 1 <= this.maxNumberOfAllContainers) {
						
						this.getCurrentPort().containers.remove(cont);
						this.currentContainers.add(cont);
						this.weightOfContainers += cont.getWeight();
						this.numberOfRefrigeratedContainers += 1;
						this.numberOfHeavyContainers += 1;
						this.numberOfContainers += 1;	
						System.out.println("\n...Container Loaded to Ship...\n");
						return true;
					}
					
					else {
						System.out.println("...Container is not Loaded to Ship...\n"
								+ "...Try Again...\n");
						return false;
					}
				}
				
				else if (cont instanceof LiquidContainer == true) {
					if (this.weightOfContainers + cont.getWeight() <= this.totalWeightCapacity
							&& this.numberOfLiquidContainers + 1 <= this.maxNumberOfLiquidContainers
							&& this.numberOfHeavyContainers + 1 <= this.maxNumberOfHeavyContainers
							&& this.numberOfContainers + 1 <= this.maxNumberOfAllContainers) {
						
						this.getCurrentPort().containers.remove(cont);
						this.currentContainers.add(cont);
						this.weightOfContainers += cont.getWeight();
						this.numberOfLiquidContainers += 1;
						this.numberOfHeavyContainers += 1;
						this.numberOfContainers += 1;
						System.out.println("\n...Container Loaded to Ship...\n");
						return true;
					}
					else {
						System.out.println("...Container is not Loaded to Ship...\n"
								+ "...Try Again...\n");
						return false;
					}
				}
				
				else if (cont instanceof HeavyContainer == true) {
					if (this.weightOfContainers + cont.getWeight() <= this.totalWeightCapacity
							&& this.numberOfHeavyContainers + 1 <= this.maxNumberOfHeavyContainers
							&& this.numberOfContainers + 1 <= this.maxNumberOfAllContainers) {
						
						this.getCurrentPort().containers.remove(cont);
						this.currentContainers.add(cont);
						this.weightOfContainers += cont.getWeight();
						this.numberOfHeavyContainers += 1;
						this.numberOfContainers += 1;
						System.out.println("\n...Container Loaded to Ship...\n");
						return true;
					}
					else {
						System.out.println("...Container is not Loaded to Ship...\n"
								+ "...Try Again...\n");
						return false;
					}
				}
				
				else if (cont instanceof BasicContainer) {		// container instance of BasicContainer
					if (this.weightOfContainers + cont.getWeight() <= this.totalWeightCapacity
							&& this.numberOfContainers + 1 <= this.maxNumberOfAllContainers) {
						
						this.getCurrentPort().containers.remove(cont);
						this.currentContainers.add(cont);
						this.weightOfContainers += cont.getWeight();
						this.numberOfContainers += 1;
						System.out.println("\n...Container Loaded to Ship...\n");
						return true;
					}
					else {
						System.out.println("...Container is not Loaded to Ship...\n"
								+ "...Try Again...\n");
						return false;
					}
				}
				
				else {
					return false;
				}
			}
		
		else {
			System.out.println("\n...Container Not Present in Port...\n");
			return false;
		}
	}
	
	//Unloads the container to the port if the given container is in the ship.
	@Override
	public boolean unLoad(Container cont) {
	
		if (this.currentContainers.contains(cont)) {
			
			this.getCurrentPort().containers.add(cont);
			this.currentContainers.remove(cont);
			this.weightOfContainers -= cont.getWeight();
			this.numberOfContainers -= 1;
			
			if (cont instanceof HeavyContainer) {
				this.numberOfHeavyContainers -= 1;
				
				if (cont instanceof RefrigeratedContainer) {
					this.numberOfRefrigeratedContainers -= 1;
				}
				
				if (cont instanceof LiquidContainer) {
					this.numberOfLiquidContainers -= 1;
				}
			}
			System.out.println("\n...Container Un-Loaded Sucessfully...\n");
			return true;
		}
		
		else {
			System.out.println("\n...Container not Present in Ship...\n");
			return false;
		}
		
	}

	public int getID() {
		return ID;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public Port getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}	
}
