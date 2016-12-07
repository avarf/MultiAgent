// Agent stock in project DomesticRobot.mas2j

/* Initial beliefs and rules */
stockInventory(100).

/* Initial goals */
//!testgoal. // JUST FOR TESTING EACH ACTION AND ITS CORRESPONDANT JAVA FUNCTION

/* Plans */
+!testgoal: true <- deliver_item_robot(5).

+!order(Deliverpoint, Otype, Oquant, OID): Oquant > stockInventory(N) <-
								.send(bearingprovider, achieve, order_bearing(Oquant)); // maybe it is better to wait for the response???
								!order(Otype, Oquant, OID).

+!order(Deliverpoint, Otype, Oquant, OID) : stockInventory(N) > Oquant <-
								.send(robota, achieve, at(robota, stock));
								!robotpos;
								deliver_item_robot(Oquant);
								+stockInventory((N - Oquant));
								.send(robota, achieve, deliverto(Deliverpoint, Otype, Oquant, OID)).
				
+!robotpos : at(robota,stock) <- true.
+!robotpos : not at(robota,stock) <- !robotpos.
