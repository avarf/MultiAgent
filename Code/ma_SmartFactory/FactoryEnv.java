import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;

//import com.sun.java.util.jar.pack.ConstantPool.LiteralEntry;

public class FactoryEnv extends Environment {

    // common literals

	
	public static final Literal stock = Literal.parseLiteral("at(robota, stock)");
	public static final Literal delivery = Literal.parseLiteral("at(robota,delivery)");
	public static final Literal machine = Literal.parseLiteral("at(robota,machine)");
	public static final Literal traystock = Literal.parseLiteral("at(robota,traystock)");
	
    int flag = 0;
    static Logger logger = Logger.getLogger(FactoryEnv.class.getName());

    FactoryModel model; // the model of the grid
    
    @Override
    public void init(String[] args) {
        model = new FactoryModel();
        
        if (args.length == 1 && args[0].equals("gui")) { 
            FactoryView view  = new FactoryView(model);
            model.setView(view);
        }
        
        updatePercepts("sample ag");
    }
    
    /** creates the agents percepts based on the FactoryModel */
    void updatePercepts(String ag) {
        
    	// clear the percepts of the agents
        clearPercepts("robota");
        clearPercepts("stock");
        //addPercept("robota", stock);
        //addPercept("robota", hello);
    	
    	
        Location lRobotA = model.getAgPos(0);
        if (lRobotA.equals(model.lStock)) {
          addPercept("robota", stock);
          }
        
        if (lRobotA.equals(model.lDelivery)) {
          addPercept("robota", delivery);
        }
        
        //logger.info("***** Robota Percept After Update ******"+getPercepts(ag));
        //robotLocation();
        

    	
    }
    

    @Override
    public boolean executeAction(String ag, Structure action) {
    	System.out.println("["+ag+"] doing: "+action);
    	
    	// returns the atom at the beginning of the action as a string
    	String afunctor = action.getFunctor();
    	String pos = new String();
        boolean result = false;
        int ordtyp = 0;
        int ordquant = 0;
        int ordID = 0;
        
        //addPercept(Literal.parseLiteral("dirty"));
        // detecting action and calling the equivalant function from model
        if (afunctor.equals("move_towards")) {
        	String l = action.getTerm(0).toString();
        	Location dest = null;
        	if (l.equals("machine")){
        		dest = model.lAMachine;
        	} else if(l.equals("stock")){
        		dest = model.lStock;
        	} else if(l.equals("traystock")){
        		dest = model.lTrayStock;
        	} else if(l.equals("delivery")){
        		dest = model.lDelivery;
        	}
        	
        	try {
                result = model.moveTowards(dest);
                //logger.info("***** Robota Percept "+getPercepts(ag));
                //addPercept("robota", as);
            } catch (Exception e) {
                e.printStackTrace();
            }
			
		} else if (afunctor.equals("deliver_payload")) {
			try {
				
				pos = action.getTerm(0).toString();
				ordtyp = (int)((NumberTerm)action.getTerm(1)).solve();
				ordquant = (int)((NumberTerm)action.getTerm(2)).solve();
				ordID = (int)((NumberTerm)action.getTerm(3)).solve();
				
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
//			String delivpoint = action.getTerm(0).toString();
//			ordtyp = (int)((NumberTerm)action.getTerm(1)).solve();
//			ordquant = (int)((NumberTerm)action.getTerm(2)).solve();
//			ordID = (int)((NumberTerm)action.getTerm(3)).solve();
			result = model.deliverPayload(pos, ordtyp, ordquant, ordID);
			logger.info("******** deliver_payload TEST INFO(Just for deliverying to machine): " 
					+ model.machineBInventory );
			
		} else if (afunctor.equals("deliver_item_robot")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
				result = model.deliverItemtoRobot(ordquant);
				logger.info("******** deliver_item_robot TEST INFO: "+model.cargoQuantity);
			} catch (Exception e) {
				//logger.info("Failed to execute action "+action);
				logger.info("********exception: "+e);
			}
			//ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			//result = model.deliverItemtoRobot(ordquant);
					
		} else if (afunctor.equals("deliver_tray_robot")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			//ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			result = model.deliverTraytoRobot(ordquant);
			logger.info("******** deliver_tray_robot TEST INFO: "+model.cargoTrayQuantity);
			
		} else if (afunctor.equals("add_bearingt_stock")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			//ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			result = model.addBearingtStock(ordquant);
			logger.info("******** add_bearingt_stock TEST INFO: "+model.stockInventory);
			
		} else if (afunctor.equals("assembel_bearingboxes")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			//ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			result = model.assembleBearingBoxes(ordquant);
			logger.info("******** assembel_bearingboxes TEST INFO: " + result);
			logger.info("******** assembel_bearingboxes TEST INFO: "+model.assembledBearingBox);
			
		} else {
			logger.info("INSIDE LAST ELSE _ Failed to execute action "+action);
		}
        
        if(result){
        	updatePercepts(ag); // DO WE REALLY NEED THIS ???
        	try { Thread.sleep(500); } catch (Exception e) {}
        }
        return result;
		
    }

//    
//    // get the robot location and adds it to its percepts
//    public void robotLocation(){
//        // get the robot location
//        Location lRobotA = model.getAgPos(0);
//
//        logger.info("***** Inside robotlocation ******** ");
//        Literal amachine = Literal.parseLiteral("at(robota,macine)");
//        if (lRobotA.equals(model.lAMachine)) {
//            addPercept("robota", amachine);
//            return ;
//        }if (lRobotA.equals(model.lStock)) {
//            //addPercept("robota", Literal.parseLiteral("at(robota,stock)"));
//            addPercept("robota", atstock);
//        	return ;
//        }if (lRobotA.equals(model.lDelivery)) {
//            addPercept("robota", Literal.parseLiteral("at(robota,delivery)"));
//            return ;
//        }if (lRobotA.equals(model.lTrayStock)) {
//            addPercept("robota", Literal.parseLiteral("at(robota,traystock)"));
//            return ;
//        } else {
//        	addPercept("robota", hello);
//        }
//        
//    }
    
    
}
