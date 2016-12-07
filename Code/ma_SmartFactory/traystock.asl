// Agent traystock in project DomesticRobot.mas2j
/* Initial beliefs and rules */
traystockInventory(1000).

/* Initial goals */
//!testgoal.
//!start.

/* Plans */
+!testgoal : true <- deliver_tray_robot(15).

+!start : true <- .send(robotb, achieve, at(robotb, traystock));
								!robotpos(robotb).
								

// like bearing stock we have to first check the number of assembly trays and then send the ordered ones but
// for now we just deliver the assembly trays

+!deliver_tray_machine(Oquant, OID) : true <-
								.send(robotb, achieve, at(robotb, traystock));
								!robotpos(robotb);
								deliver_tray_robot(Oquant);
								.send(robota, achieve, deliverto(machine, -1, Oquant, OID)).

+!robotpos(R) : at(R,traystock) <- true.
+!robotpos(R) : not at(R,traystock) <- !robotpos(R).
