import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;


public class FactoryEnv extends Environment {

    // common literals

	
	public static final Literal stock1 = Literal.parseLiteral("at(robota1, stock)");
	public static final Literal stock2 = Literal.parseLiteral("at(robota2, stock)");
	
	public static final Literal delivery1 = Literal.parseLiteral("at(robota1,delivery)");
	public static final Literal delivery2 = Literal.parseLiteral("at(robota2,delivery)");
	
	public static final Literal machine1 = Literal.parseLiteral("at(robota1,machine)");
	public static final Literal machine2 = Literal.parseLiteral("at(robota2,machine)");
	
	public static final Literal traystock1 = Literal.parseLiteral("at(robota1,traystock)");
	public static final Literal traystock2 = Literal.parseLiteral("at(robota2,traystock)");
	
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
    void updatePercepts(String ag){
    	//logger.info("---Inside update and agent is:  "+ag);
    	if(ag.equals("robota1")){
    		clearPercepts("robota1");
    		Location lRobot = model.getAgPos(0);
    		
    		if(lRobot.equals(model.lStock)){
    			addPercept("robota1", stock1);
    		}
    		
    		if(lRobot.equals(model.lDelivery)){
    			addPercept("robota1", delivery1);
    		}
    		
    		if(lRobot.equals(model.lAMachine)){
    			addPercept("robota1", machine1);
    		}
    	}else if (ag.equals("robota2")) {
			clearPercepts("robota2");
			Location lRobot = model.getAgPos(1);
			
    		if(lRobot.equals(model.lStock)){
    			addPercept("robota2", stock2);
    		}
    		
    		if(lRobot.equals(model.lDelivery)){
    			addPercept("robota2", delivery2);
    		}
    		
    		if(lRobot.equals(model.lAMachine)){
    			addPercept("robota2", machine2);
    		}
    		
    		if (lRobot.equals(model.lTrayStock)) {
				addPercept("robota2", traystock2);
			}
		}
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
                result = model.moveTowards(ag, dest);
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
			result = model.deliverPayload(pos, ordtyp, ordquant, ordID);
			logger.info("******** deliver_payload TEST INFO(Just for deliverying to machine): " 
					+ model.machineBInventory+result );
			
		} else if (afunctor.equals("deliver_item_robot")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
				result = model.deliverItemtoRobot(ordquant);
				logger.info("******** deliver_item_robot TEST INFO: "+model.cargoQuantity);
			} catch (Exception e) {
				logger.info("********exception: "+e);
			}
		} else if (afunctor.equals("deliver_tray_robot")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			result = model.deliverTraytoRobot(ordquant);
			logger.info("******** deliver_tray_robot TEST INFO: "+model.cargoTrayQuantity+result);
			
		} else if (afunctor.equals("add_bearingt_stock")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			result = model.addBearingtStock(ordquant);
			logger.info("******** add_bearingt_stock TEST INFO: "+model.stockInventory);
			
		} else if (afunctor.equals("assembel_bearingboxes")) {
			try {
				ordquant = (int)((NumberTerm)action.getTerm(0)).solve();
			} catch (Exception e) {
				logger.info("Failed to execute action "+action);
			}
			
			result = model.assembleBearingBoxes(ordquant);
			logger.info("******** assembel_bearingboxes TEST INFO: "+model.assembledBearingBox);
			
		} else if(afunctor.equals("clearb")) {
	        clearPercepts(ag);
	        return true;
		} else {
			logger.info("INSIDE LAST ELSE _ Failed to execute action "+action);
		}
        
        if(result){
        	updatePercepts(ag);
        	try { Thread.sleep(500); } catch (Exception e) {}
        }
        return result;
		
    }
    
}
