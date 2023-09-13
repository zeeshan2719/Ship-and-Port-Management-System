package main;


import java.io.FileNotFoundException;

import java.util.Scanner;

import containers.*;
import ports.Port;
import ships.Ship;

import java.util.ArrayList;


public class Main1 {
	
	public static void main(String[] args) throws FileNotFoundException {

		//Keeps track of the containers that get created.
		ArrayList<Container> allContainers = new ArrayList<Container>();
		// Keeps track of the ships that get created.
		ArrayList<Ship> ships = new ArrayList<Ship>();
		//Keeps track of the ports that get created.
		ArrayList<Port> ports = new ArrayList<Port>();
		int currentPortID = 0;
		int currentShipID = 0;
		int currentContainerID = 0;
		int currentActionID = 0;
		//Stores the input line to create a new port.
		ArrayList<String> portData = new ArrayList<String>();
		//Stores the input line to create a new ship.
		ArrayList<String> shipData = new ArrayList<String>();
		//Stores the input line to create a new container.
		ArrayList<String> containerData = new ArrayList<String>();
		//Stores the input line for the actions.
		ArrayList<String> actionData = new ArrayList<String>();
		int n;
		int N =1;
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		while(N==1){
			System.out.println("\nEnter :\n"
					+ "1 For Creating Port\n"
					+ "2 For Creating Ship\n"
					+ "3 For Creating Container\n"
					+ "4 For Loading Container\n"
					+ "5 For Un-Loading Container\n"
					+ "6 For Sailing Ship\n"
					+ "7 For RE-Fueling Ship\n"
					+ "0 For Exit\n");
			n = sc1.nextInt();
			String inputLine;
			String[] tempArray;
			Ship s;

			if (n==3) {
				// inputs with number 1
				System.out.println("Enter : \n'Port ID' and 'Weight of the Container'\n separated by Space\n"
						+ "For Basic Container Weight Should be less that 3000\n"
						+ "For Refrigerated Container Add 'R' after the weight separated by Space\n"
						+ "For Liquid Container Add 'L' after the weight separated by Space\n");
				inputLine = sc.nextLine();
				inputLine = "1 " + inputLine;
				tempArray = inputLine.split(" ");
				containerData.add(currentContainerID, inputLine);
				// BasicContainer or HeavyContainer
				if (containerData.get(currentContainerID).split(" ").length == 3) {

					if (Integer.parseInt(containerData.get(currentContainerID).split(" ")[2]) <= 3000) {
						allContainers.add(currentContainerID, new BasicContainer(currentContainerID,						// ID							// ID
							Integer.parseInt(containerData.get(currentContainerID).split(" ")[2])));	// Weight
					}
					else {
						allContainers.add(currentContainerID, new HeavyContainer(currentContainerID,						// ID								// ID
							Integer.parseInt(containerData.get(currentContainerID).split(" ")[2])));	// Weight
					}
				}

				// RefrigeratedContainer or LiquidContainer
				else {
					if (containerData.get(currentContainerID).split(" ")[3].equals("R") == true) {
						allContainers.add(currentContainerID, new RefrigeratedContainer(currentContainerID,				 // ID							 // ID
							Integer.parseInt(containerData.get(currentContainerID).split(" ")[2])));	 // Weight
					}
					else {
						allContainers.add(currentContainerID, new LiquidContainer(currentContainerID,	            		// ID							// Port ID
							Integer.parseInt(containerData.get(currentContainerID).split(" ")[2])));	// Weight
					}
				}

				ports.get(Integer.parseInt(containerData.get(currentContainerID).split(" ")[1]))
				.containers.add(allContainers.get(currentContainerID));
				currentContainerID += 1;
			}

			else if (n==2) {
				// inputs with number 2
				System.out.println("Enter :\n"
						+ "Port ID\n"
						+ "Total Weight Capacity of the Ship\n"
						+ "Max Number Of All Containers Ship can take\n"
						+ "Max Number Of Heavy Containers Ship can take\n"
						+ "Max Number Of Refrigerated Containers Ship can take\n"
						+ "Max Number Of Liquid Containers Ship can take\n"
						+ "Fuel Consumption Per KM\nSeparated by Space");
				inputLine = sc.nextLine();
				inputLine = "2 " + inputLine;
				tempArray = inputLine.split(" ");
				shipData.add(currentShipID, inputLine);
				s = new Ship(currentShipID, 											// ID
					ports.get(Integer.parseInt(shipData.get(currentShipID).split(" ")[1])),	// Port
					Integer.parseInt(shipData.get(currentShipID).split(" ")[2]),			// MaxWeight
					Integer.parseInt(shipData.get(currentShipID).split(" ")[3]),			// Max # of Container
					Integer.parseInt(shipData.get(currentShipID).split(" ")[4]),			// Max # of Heavy Container
					Integer.parseInt(shipData.get(currentShipID).split(" ")[5]),			// Max # of Refrigerated Container
					Integer.parseInt(shipData.get(currentShipID).split(" ")[6]),			// Max # of Liquid Container
					Double.parseDouble(shipData.get(currentShipID).split(" ")[7]));
				ships.add(currentShipID, s);				// Fuel Consumption Per KM
				s.getCurrentPort().incomingShip(s);
				currentShipID += 1;
			}

			else if (n==1) {	// inputs with number 3
				System.out.println("Enter X and Y Co-ordinate Separated by Space");
				inputLine = sc.nextLine();
				inputLine = "3 " + inputLine;
				tempArray = inputLine.split(" ");
				portData.add(currentPortID, inputLine);
				ports.add(currentPortID, new Port(currentPortID, 										// ID
					Double.parseDouble(portData.get(currentPortID).split(" ")[1]),		// X
					Double.parseDouble(portData.get(currentPortID).split(" ")[2])));	// Y
				currentPortID += 1;
			}

			else {	
			// inputs with number 3, 4, 5, 6, 7, 8
				if (n==4){
					System.out.println("Enter Ship ID and Container ID separated by space");
					inputLine = sc.nextLine();
					inputLine = "4 " + inputLine;
					tempArray = inputLine.split(" ");
					actionData.add(currentActionID, inputLine);
					ships.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[1])).
					load(allContainers.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[2])));
					currentActionID += 1;
				}
				else if (n==5){
					System.out.println("Enter Ship ID and Container ID separated by space");
					inputLine = sc.nextLine();
					inputLine = "5 " + inputLine;
					tempArray = inputLine.split(" ");
					actionData.add(currentActionID, inputLine);
					ships.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[1])).
					unLoad(allContainers.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[2])));
					currentActionID += 1;
				}
				else if (n==6){
					System.out.println("Enter Ship ID and Port ID separated by space");
					inputLine = sc.nextLine();
					inputLine = "6 " + inputLine;
					tempArray = inputLine.split(" ");
					actionData.add(currentActionID, inputLine);
					ships.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[1])).
					sailTo(ports.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[2])));
					currentActionID += 1;
				}
				else if (n==7){
					System.out.println("Enter Ship ID and Quantity of fuel separated by space");
					inputLine = sc.nextLine();
					inputLine = "7 " + inputLine;
					tempArray = inputLine.split(" ");
					actionData.add(currentActionID, inputLine);
					ships.get(Integer.parseInt(actionData.get(currentActionID).split(" ")[1])).
					reFuel(Double.parseDouble(actionData.get(currentActionID).split(" ")[2]));
					currentActionID += 1;
				}
				else if(n==0){
					break;
				}
			}	
			
			// OUTPUT
			System.out.println("--------------------------------------------------------------------------------");
		    for (Port p : ports) {
//		    	System.out.println("--------------------------------------------------------------------------------");
			    System.out.printf("Port %d: (%.2f, %.2f)\n", p.getID(), p.getX(), p.getY());

			    ArrayList<RefrigeratedContainer> refrigerated = new ArrayList<RefrigeratedContainer>();
			    ArrayList<LiquidContainer> liquid = new ArrayList<LiquidContainer>();
			    ArrayList<HeavyContainer> heavy = new ArrayList<HeavyContainer>();
			    ArrayList<BasicContainer> basic = new ArrayList<BasicContainer>();

			    // Categorization of Containers
			    for (Container c : p.getCurrentContainersInPort(p.containers)) {	

				    if (c instanceof RefrigeratedContainer) {
					    refrigerated.add((RefrigeratedContainer) c);
				    }

				    else if (c instanceof LiquidContainer) {
					    liquid.add((LiquidContainer) c);
				    }

				    else if (c instanceof HeavyContainer) {
					    heavy.add((HeavyContainer) c);
				    }

				    else {
					    basic.add((BasicContainer) c);
				    }
			    }

			    // Printing Containers
			    if (basic.size()>0) {
			    	System.out.print("  BasicContainer:");
				    for (BasicContainer bc : basic ) {
				    	System.out.print(" " + bc.getID());
				    }
				    System.out.println();
			    }

			    if (heavy.size()>0) {
			    	System.out.print("  HeavyContainer:");
				    for (HeavyContainer hc : heavy ) {
				    	System.out.print(" " + hc.getID());
				    }
				    System.out.println();
			    }

			    if (refrigerated.size()>0) {
			    	System.out.print("  RefrigeratedContainer:");
				    for (RefrigeratedContainer rc : refrigerated ) {
				    	System.out.print(" " + rc.getID());
				    }
				    System.out.println();
			    }

			    if (liquid.size()>0) {
			    	System.out.print("  LiquidContainer:");
				    for (LiquidContainer lc : liquid ) {
				    	System.out.print(" " + lc.getID());
				    }
				    System.out.println();
			    }

			    for (Ship s1 : p.getCurrentShipsInPort(p.current)) {

			    	System.out.printf("  Ship %d: %.2f\n", s1.getID(), s1.getFuel());

				    ArrayList<RefrigeratedContainer> refrigeratedInShip = new ArrayList<RefrigeratedContainer>();
				    ArrayList<LiquidContainer> liquidInShip = new ArrayList<LiquidContainer>();
				    ArrayList<HeavyContainer> heavyInShip = new ArrayList<HeavyContainer>();
				    ArrayList<BasicContainer> basicInShip = new ArrayList<BasicContainer>();

				    // Categorization of Containers
				    for (Container cInShip : s1.getCurrentContainers()) {	

					    if (cInShip instanceof RefrigeratedContainer) {
						    refrigeratedInShip.add((RefrigeratedContainer) cInShip);
					    }

					    else if (cInShip instanceof LiquidContainer) {
						    liquidInShip.add((LiquidContainer) cInShip);
					    }

					    else if (cInShip instanceof HeavyContainer) {
						    heavyInShip.add((HeavyContainer) cInShip);
					    }

					    else {
						    basicInShip.add((BasicContainer) cInShip);
					    }
				    }

				    // Printing Containers
				    if (basicInShip.size()>0) {
				    	System.out.print("    BasicContainer:");
					    for (BasicContainer bc : basicInShip ) {
					    	System.out.print(" " + bc.getID());
					    }
					    System.out.println();
				    }

				    if (heavyInShip.size()>0) {
				    	System.out.print("    HeavyContainer:");
					    for (HeavyContainer hc : heavyInShip ) {
					    	System.out.print(" " + hc.getID());
					    }
					    System.out.println();
				    }

				    if (refrigeratedInShip.size()>0) {
				    	System.out.print("    RefrigeratedContainer:");
					    for (RefrigeratedContainer rc : refrigeratedInShip ) {
					    	System.out.print(" " + rc.getID());
					    }
					    System.out.println();
				    }

				    if (liquidInShip.size()>0) {
				    	System.out.print("    LiquidContainer:");
					    for (LiquidContainer lc : liquidInShip ) {
					    	System.out.print(" " + lc.getID());
					    }
					    System.out.println();
				    }
			    }
//			    System.out.println("--------------------------------------------------------------------------------");
		    }
		    System.out.println("--------------------------------------------------------------------------------");
	    }
	}		
}