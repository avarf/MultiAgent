// Agent manager in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
// if the order is valid the manager forwards the order to stock or machine based on order type
+!order(Otype, Oquant, OID): busy <- .print("BUSSSSSSSYYYYYYY"); 
									!order(Otype, Oquant, OID).

+!order(Otype, Oquant, OID): Otype = 0 <- +busy;
										.print("New order for bearings"); 
										.send(stock, achieve, order(delivery, Otype, Oquant, OID)).

+!order(Otype, Oquant, OID): Otype = 1 <- +busy;
										.print("New order for assembeled bearings"); 
										.send(machine, achieve, order(Otype, Oquant, OID)).


+!orderfinished(OID) : true <- -busy;
								.print(OID,"finished.");
								.send(customer, tell, orderisready(OID)).
