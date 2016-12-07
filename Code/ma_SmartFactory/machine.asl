// Agent machine in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */
!start.
//!testgoal.

/* Plans */
+!testgoal : true <- assembel_bearingboxes(44).
+!start : true <- !order2(1,5,12).


+!order2(Otype, Oquant, OID): true <-
								.send(stock, achieve, order(machine, Otype, Oquant, OID));
								//.send(traystock, achieve, deliver_tray_machine(Oquant, OID));
								//wait for bearing
								!robotpos(robota);
								//wait for tray
								//!robotpos(robotb);
								assembel_bearingboxes(Oquant);
								deliver_item_robot(Oquant); //delivering assembeled bearing boxes to robot
								.send(robota, achieve, deliverto(delivery, Otype, Oquant, OID)).
								//!returntray(Oquant).



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
								//.send(robotb, achieve, at(robotb, machine));
								deliver_tray_robot(Oquant);
								.send(robotb, achieve, deliverto(traystock, -1 , Oquant, 0)). 
								// ordtype: 0 -> bearing, 1 -> bearingboxes, -1 -> trays
