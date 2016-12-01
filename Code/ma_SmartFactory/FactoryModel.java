import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of Domestic Robot application */
public class FactoryModel extends GridWorldModel {
    
    // constants for the grid objects
	// these are uniq IDs for each object in the environment and we just can have access to an object with its ID
//    public static final int FRIDGE = 16;
//    public static final int OWNER  = 32;
    
    public static final int ASSEMBLYMACHINE = 10;
    public static final int STOCK = 20;
    public static final int DELIVERY = 40;
    public static final int TRAYSTOCK = 30;
    
    public static final int TRAYROBOT = 1;

    // the grid size
    public static final int GSize = 20;
    
    boolean fridgeOpen   = false; 	// whether the fridge is open
    boolean carryingBeer = false; 	// whether the robot is carrying beer
    int sipCount        = 0; 		// how many sip the owner did
    int availableBeers  = 2; 		// how many beers are available
    
    boolean machineFree = true; 	// whether the assembly machine is free or not
    boolean carryingItem = false; 	// whether the transportation robot is ccarrying something or not
    boolean carryingTray = false; 	// whether the tray transportation robot is carrying assembly tray or not
    int stockInventory = 100; 		// how many bearing are in the stock
    int trayInventory = 20; 		// how many assembly tray are in the assembly tray stock
    int machineBInventory = 0; 		// how many unassembled bearings are in the machine storage
    int machineTrayInventory = 0; 	// how many assembly trays are in the machine storage
    int machineOrderID = 0; 		// the ID of current working order
    int trayQuantity = 0; 			// how many assembly tray we need for an specific order
    int assembledBearingBox = 0; 	//number of assembled bearing boxes
    int cargoQuantity = 0; 			// how many items a transportation robot is carrying
    int cargoTrayQuantity = 0; 		// how many assembly trays a transportation robot is carrying
    int orderID = 0; 				// the ID for each order
    String orderType = "bearing"; 	// what is the order, by default it is bearing
    int orderQuantity = 10; 		// the quantity of each order

    Location lFridge = new Location(0,0);
    Location lOwner  = new Location(GSize-1,GSize-1);
    
    Location lAMachine = new Location(4,4);
    Location lStock = new Location(10,4);
    Location lDelivery = new Location(8, 8);
    Location lTrayStock = new Location(4,10);
    Location lTrayRobot = new Location(15, 15);

    public FactoryModel() {
        // create a grid with one mobile agent
        super(GSize, GSize, 1);

        // initial location of robot
        // ag code 0 means the transportation robot for transfering bearing and bearing boxes
        // ag code 1 means the assembly tray transportation robot
        setAgPos(0, 15, 15); // agent 0 is robota
        
        // initial location of fixed objects to the grid
        add(ASSEMBLYMACHINE, lAMachine);
        add(STOCK, lStock);
        add(TRAYSTOCK, lTrayStock);
        add(DELIVERY, lDelivery);
    }

    // Actions
    // we have a methode for each action
    
    // ordtype: 0 -> bearing, 1 -> bearingboxes, -1 -> trays
    boolean deliverPayload(String deliverpoint, int ordtype, int ordquant, int ordID){
    	if (deliverpoint.equals("machine")) {
    		if (ordtype == 0) {
				machineBInventory = machineBInventory + ordquant;
			} else {
				machineTrayInventory = machineTrayInventory + ordquant;
			}
    		cargoQuantity = 0;
			carryingItem = false;
    		return true;
			
		} else if(deliverpoint.equals("trayStock")) {
			trayInventory += ordquant;
			cargoQuantity = 0;
			carryingItem = false;
			return true;
			
		}else if(deliverpoint.equals("delivery")){
			cargoQuantity = 0;
			carryingItem = false;
			return true;
		}
    	
		return false;
    }
    
    boolean deliverItemtoRobot(int ordquant){
    	//!carryingItem
    	if(!carryingItem){
    		carryingItem = true;
    		cargoQuantity = ordquant;
//    		if (view != null) {
//                view.update(lAMachine.x, lAMachine.y);
//            	view.update(lStock.x, lStock.y);
//            	view.update(lTrayStock.x, lTrayStock.y);
//            	view.update(lDelivery.x, lDelivery.y);	
//            }
    		return true;
    	} else {
			return false;
		}
    }
    
    boolean deliverTraytoRobot(int ordquant){
    	if(!carryingTray){
    		carryingTray = true;
    		cargoTrayQuantity = ordquant;
    		return true;
    	} else {
			return false;
		}
    }
    
    // add new bearings to stock inventory - after ordering bearings to other providers
    boolean addBearingtStock(int amount){
    	stockInventory = stockInventory + amount;
    	return true;
    }
    
    boolean assembleBearingBoxes(int ordquant){
    	assembledBearingBox = assembledBearingBox + ordquant;
		machineBInventory = machineBInventory - ordquant;
		machineFree = true;
		if (view != null){
            view.update(lAMachine.x, lAMachine.y);
		}
		return true;
    }
    
    boolean moveTowards(Location dest) {
        Location r1 = getAgPos(0);
        if (r1.x < dest.x)        r1.x++;
        else if (r1.x > dest.x)   r1.x--;
        if (r1.y < dest.y)        r1.y++;
        else if (r1.y > dest.y)   r1.y--;
        setAgPos(0, r1); // move the robot in the grid
        
        // repaint the grid and whole environment
        if (view != null) {
            view.update(lAMachine.x, lAMachine.y);
        	view.update(lStock.x, lStock.y);
        	view.update(lTrayStock.x, lTrayStock.y);
        	view.update(lDelivery.x, lDelivery.y);
        	
        }
        return true;
    }
    
    boolean moveTowards2(Location dest){
    	Location r1 = getAgPos(0);
    	
    	while(r1.x != dest.x){
    		if (r1.x < dest.x)        r1.x++;
            else if (r1.x > dest.x)   r1.x--;
    		
    		setAgPos(0, r1);
            view.update(lAMachine.x, lAMachine.y);
        	view.update(lStock.x, lStock.y);
        	view.update(lTrayStock.x, lTrayStock.y);
        	view.update(lDelivery.x, lDelivery.y);
    	}
    	
    	while(r1.y != dest.y){
    		if (r1.y < dest.y)        r1.y++;
            else if (r1.y > dest.y)   r1.y--;
    		
    		setAgPos(0, r1);
            view.update(lAMachine.x, lAMachine.y);
        	view.update(lStock.x, lStock.y);
        	view.update(lTrayStock.x, lTrayStock.y);
        	view.update(lDelivery.x, lDelivery.y);
    	}
    	setAgPos(0, r1);
    	
        // repaint the environment locations
        if (view != null) {
            view.update(lAMachine.x, lAMachine.y);
        	view.update(lStock.x, lStock.y);
        	view.update(lTrayStock.x, lTrayStock.y);
        	view.update(lDelivery.x, lDelivery.y);
        	
        }
        return true;
    }
}
