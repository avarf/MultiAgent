// Agent traystock in project DomesticRobot.mas2j
/* Initial beliefs and rules */
traystockInventory(1000).

/* Initial goals */
//!test.

/* Plans */
+!test : true <- !deliver_tray_machine(14, 111).
// like bearing stock we have to first check the number of assembly trays and then send the ordered ones but
// for now we just deliver the assembly trays

+!deliver_tray_machine(Oquant, OID) : true <-
								.send(robota2, achieve, at(robota2, traystock));
								!robotpos(robota2);
								deliver_tray_robot(Oquant);
								.send(robota2, achieve, deliverto(robota2, machine, -1, Oquant, OID)).

+!robotpos(R) : at(R,traystock) <- true.
+!robotpos(R) : not at(R,traystock) <- !robotpos(R).
