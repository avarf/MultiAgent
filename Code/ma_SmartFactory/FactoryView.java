import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

    
/** class that implements the View of Domestic Robot application */
public class FactoryView extends GridWorldView {

    FactoryModel hmodel;
    
    public FactoryView(FactoryModel model) {
        super(model, "Smart Factory", 700);
        hmodel = model;
        defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        Location lRobot = hmodel.getAgPos(0);
        // Location lRobot2 = hmodel.getAgPos(1);
        super.drawAgent(g, x, y, Color.lightGray, -1);
        switch (object) {
        	case FactoryModel.ASSEMBLYMACHINE:
        		if (lRobot.equals(hmodel.lAMachine)) {
        			super.drawAgent(g, x, y, Color.yellow, -1);
        		}
        		g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Machine");
        		break;
        	case FactoryModel.STOCK:
        		if (lRobot.equals(hmodel.lStock)) {
        			super.drawAgent(g, x, y, Color.yellow, -1);
        		}
        		g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Stock");  
        		break;
        	case FactoryModel.DELIVERY:
        		if (lRobot.equals(hmodel.lDelivery)) {
        			super.drawAgent(g, x, y, Color.yellow, -1);
        		}
        		g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Delivery");
        		break;
        	case FactoryModel.TRAYSTOCK:
        		if (lRobot.equals(hmodel.lTrayStock)) {
        			super.drawAgent(g, x, y, Color.yellow, -1);
        		}
        		g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "TrayStock");
        		break;
        }
        repaint();
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lRobot = hmodel.getAgPos(0);
        // Location lRobot = hmodel.getAgPos(1);
        
        if (!lRobot.equals(hmodel.lAMachine) && !lRobot.equals(hmodel.lStock)) {
            c = Color.yellow;
            if (hmodel.carryingBeer) c = Color.orange;
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, x, y, defaultFont, "Robot");
        }
    }
}
