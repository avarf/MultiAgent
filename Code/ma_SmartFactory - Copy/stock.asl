// Agent stock in project DomesticRobot.mas2j

/* Initial beliefs and rules */
stockInventory(100).

/* Initial goals */
//!testgoal. // JUST FOR TESTING EACH ACTION AND ITS CORRESPONDANT JAVA FUNCTION
//!test.

/* Plans */
+!test: true <- !order(delivery,0,10,111).
+!testgoal: true <- deliver_item_robot(5).

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
				
//+!robotpos : at(robota1,stock) <- true.
//+!robotpos : not at(robota1,stock) <- !robotpos.
+!robotpos(R) : at(R,stock) <- true.
+!robotpos(R) : not at(R,stock) <- !robotpos(R).



+!order2(Deliverpoint, Otype, Oquant, OID) : stockInventory(N) > Oquant <-
								.send(robota2, achieve, at(robota2, stock));
								!robotpos2(R);
								deliver_item_robot(Oquant);
								+stockInventory((N - Oquant));
								//.send(robota1, achieve, deliverto(Deliverpoint, Otype, Oquant, OID)).
								.send(robota2, achieve, deliverto(robota2, Deliverpoint, Otype, Oquant, OID)).
								
+!robotpos2(R) : at(R,stock) <- true.
+!robotpos2(R) : not at(R,stock) <- !robotpos2(R).
