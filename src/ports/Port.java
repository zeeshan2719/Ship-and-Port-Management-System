
package ports;

import java.util.ArrayList;
import containers.Container;
import ships.Ship;

interface InterPort {
	
	void incomingShip(Ship s);
	
	void outgoingShip(Ship s);
}

public class Port implements InterPort {
	
	private final int ID;
	private final double X;
	private final double Y;
	//Keeps track of the containers currently in the port.
	public ArrayList<Container> containers = new ArrayList<Container>();
	//Keeps track of every ship that has visited.
	public ArrayList<Ship> history = new ArrayList<Ship>();
	//Keeps track of the ships currently in the port.
	public ArrayList<Ship> current = new ArrayList<Ship>();
	public Port(int ID, double X, double Y) {
		
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	
	public double getDistance(Port other) {
		System.out.println("\nTotal Distance : "+ Math.sqrt(Math.pow(other.X - this.X, 2) + Math.pow(other.Y - this.Y, 2))+"\n");
		return Math.sqrt(Math.pow(other.X - this.X, 2) + Math.pow(other.Y - this.Y, 2));
	
	}
	
	//Adds the ship to the current ArrayList of the port.
	@Override
	public void incomingShip(Ship s) {
		if (current.contains(s) == false) { 	// NO DUPLICATES
			current.add(s);
			System.out.println("\n...Ship : "+s.getID()+" Entered the Port... "+this.getID()+"\n");
		}
	}
	
	//Adds the ship to the history ArrayList of the port.
	@Override
	public void outgoingShip(Ship s) {
		if (history.contains(s) == false) {		// NO DUPLICATES
			history.add(s);
			
			System.out.println("\n...Ship : "+s.getID()+" has left the Port... "+this.getID()+"\n");
		}
		current.remove(s);	
	}

	public int getID() {
		return ID;
	}
	
	public double getX() {
		return X;
	}
	
	public double getY() {
		return Y;
	}

	//Sorts Containers in Port. 
	public ArrayList<Container> getCurrentContainersInPort(ArrayList<Container> c) {
		
		ArrayList<Container> sortedContainers = new ArrayList<Container>();
		sortedContainers.addAll(c);
	   
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
	//Sorts Ships in Port. 
	public ArrayList<Ship> getCurrentShipsInPort(ArrayList<Ship> s) {
		
		ArrayList<Ship> sortedShips = new ArrayList<Ship>();
		sortedShips.addAll(s);
	   
		int length = sortedShips.size();
		
		for (int i = 0; i < length; i++) {
	        for (int j = i + 1; j < length; j++) {
	            Ship temp;
	            if (sortedShips.get(i).getID() > sortedShips.get(j).getID()) {
	                temp = sortedShips.get(i);
	                sortedShips.remove(i);
	                sortedShips.add(i, sortedShips.get(j-1));
	                sortedShips.remove(j);
	                sortedShips.add(j, temp);
	            }
	        }
	    }
	return sortedShips;		
	}
//	public static void main(String[] args) throws Exception{
//		Port p1 =new Port(190, 3.0, 4.0);
//	}
	
}

