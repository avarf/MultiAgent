// Agent machine in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */
//!test.
//!test2.
//!testgoal.

/* Plans */
+!testgoal : true <- deliver_tray_robot(44).
+!test : true <- !order2(1,5,12).
+!test2 : true <- 
					.send(traystock, achieve, deliver_tray_machine(10, 444));
					!robotpos(robota2);
					!returntray(10).

+!order2(Otype, Oquant, OID): true <-
								.send(stock, achieve, order(machine, Otype, Oquant, OID));
								.send(traystock, achieve, deliver_tray_machine(Oquant, OID));
								//wait for bearing
								!robotpos(robota1);
								//wait for tray
								!robotpos(robota2);
								assembel_bearingboxes(Oquant);
								deliver_item_robot(Oquant); //delivering assembeled bearing boxes to robota1
								.send(robota1, achieve, deliverto(robota1, delivery, Otype, Oquant, OID));
								!returntray(Oquant).



+!order(Otype, Oquant, OID): true <-
								.send(stock, achieve, order(machine, Otype, Oquant, OID));
								.send(traystock, achieve, deliver_tray_machine(Oquant, OID));
								//wait for bearing
								!robotpos(robota);
								//wait for tray
								!robotpos(robotb);
								assembel_bearingboxes(Oquant);
								deliver_item_robot(Oquant); //delivering assembeled bearing boxes to robot
								.send(robota, achieve, deliverto(delivery, Otype, Oquant, OID));
								!returntray(Oquant).

+!robotpos(R) : at(R,machine) <- true.
+!robotpos(R) : not at(R,machine) <- !robotpos(R).

+!returntray(Oquant) : true <-
								.print("*******inside returnTray");
								.send(robota2, achieve, at(robota2, machine));
								!robotpos(robota2);
								deliver_tray_robot(Oquant);
								.send(robota2, achieve, deliverto(robota2, traystock, -1 , Oquant, 0)). 
								// ordtype: 0 -> bearing, 1 -> bearingboxes, -1 -> trays
