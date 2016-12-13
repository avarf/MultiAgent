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
    	int agg = 0;
        //Location lRobot = hmodel.getAgPos(0);
    	Location lRobot = hmodel.getAgPos(agg);
    	
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
        int agg = 0;
    	//Location lRobot = hmodel.getAgPos(0);
        Location lRobot = hmodel.getAgPos(agg);
        
        if (!lRobot.equals(hmodel.lAMachine) && !lRobot.equals(hmodel.lStock)) {
            c = Color.yellow;
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            //super.drawString(g, x, y, defaultFont, "Robot");
        }
    }
    
//    public void paintComponent(Graphics g) {
//        
//        for(int row = 0; row<gridSize; row++) {
//            for(int col = 0; col<gridSize; col++) {
//            
//                g.setColor(new Color(185, 58, 17));
//                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
//                g.setColor(Color.gray);
//                g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
//                
//                if(planet[col][row] != null) {
//                                
//                    if(col == middle && row == middle) {
//                        g.setColor(Color.white);    
//                        g.fillRect((col * cellWidth) +3, (row * cellHeight) +3, cellWidth - 5, cellHeight - 5);
//                    }
//                    else {
//                        Resource r = (Resource) planet[col][row];
//                        int type = r.getType();
//                        int amount = r.getAmount();
//                        switch(type) {
//                            case 1:
//                                g.setColor(Color.orange);   
//                                break;
//                            case 2:
//                                g.setColor(Color.green);
//                                break;
//                            case 3:
//                                g.setColor(Color.blue);
//                                break;
//                        }
//                        
//                        g.fillOval(col*cellWidth +3, row*cellHeight +3, cellWidth - 5, cellHeight - 5);
//                        g.setColor(Color.black);
//                        g.setFont(new Font("Arial", Font.PLAIN, 11));
//                        g.drawString(""+amount, (col * cellWidth) +8, (row * cellHeight) +14);
//                    }
//
//                }
//
//            }
//        }
//        
//        g.setColor(Color.black);
//        g.fillRoundRect(agent1[0]*cellWidth +3, agent1[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
//        g.setColor(Color.white);
//        g.drawString("A", (agent1[0]*cellWidth) +7, (agent1[1]*cellHeight) +14);
//
//        g.setColor(Color.black);
//        g.fillRoundRect(agent2[0]*cellWidth +3, agent2[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
//        g.setColor(Color.white);
//        g.drawString("B", (agent2[0]*cellWidth) +7, (agent2[1]*cellHeight) +14);
//
//        g.setColor(Color.black);
//        g.fillRoundRect(agent3[0]*cellWidth +3, agent3[1]*cellHeight +3, cellWidth - 5, cellHeight - 5, 3, 3);
//        g.setColor(Color.white);
//        g.drawString("C", (agent3[0]*cellWidth) +7, (agent3[1]*cellHeight) +14);
//
//    }
//    
//    public void update() {
//        
//        //planet = environment.getPlanet();
//        agent1 = environment.geta1();
//        agent2 = environment.geta2();
//        agent3 = environment.geta3();
//        repaint();
//    }
}
