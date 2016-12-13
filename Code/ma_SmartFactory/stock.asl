// Agent stock in project DomesticRobot.mas2j

/* Initial beliefs and rules */
stockInventory(100).

/* Initial goals */
//!test.

/* Plans */
+!test: true <- !order(delivery,0,10,111).

+!order(Deliverpoint, Otype, Oquant, OID): Oquant > stockInventory(N) <-
								.send(bearingprovider, achieve, order_bearing(Oquant)); // maybe it is better to wait for the response???
								!order(Otype, Oquant, OID).

+!order(Deliverpoint, Otype, Oquant, OID) : stockInventory(N) > Oquant <-
								.send(robota1, achieve, at(robota1, stock));
								!robotpos(robota1);
								deliver_item_robot(Oquant);
								+stockInventory((N - Oquant));
								//.send(robota1, achieve, deliverto(Deliverpoint, Otype, Oquant, OID)).
								.send(robota1, achieve, deliverto(robota1, Deliverpoint, Otype, Oquant, OID)).

+!robotpos(R) : at(R,stock) <- true.
+!robotpos(R) : not at(R,stock) <- !robotpos(R).
