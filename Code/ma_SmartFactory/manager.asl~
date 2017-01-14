// Agent manager in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
// when manager recieves an order it first checks it to see if it is valid or not
// if it is not valid the order will be rejected
// if the order is valid the manager forwards the order to stock or machine based on order type
+!order(Otype, Oquant, OID): Otype = 0 <- .print("New order for bearings"); 
										   .send(stock, achieve, order(delivery, Otype, Oquant, OID)).

+!order(Otype, Oquant, OID): Otype = 1 <- .print("New order for assembeled bearings"); 
											.send(machine, achieve, order(Otype, Oquant, OID)).
//+!order(Otype, Oquant, OID): check validity
