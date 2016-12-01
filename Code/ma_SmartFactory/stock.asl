// Agent stock in project DomesticRobot.mas2j

/* Initial beliefs and rules */
stockInventory(100).

/* Initial goals */
//!testgoal. // JUST FOR TESTING EACH ACTION AND ITS CORRESPONDANT JAVA FUNCTION
//!sendorder(R, Deliverpoint, Otype, Oquant, OID).
//!t1.
//!tgoal.

/* Plans */
//+!start : true <- .get_stock_inventory(N).
+!testgoal: true <- deliver_item_robot(5).

+!order(Deliverpoint, Otype, Oquant, OID): Oquant > stockInventory(N) <-
								.send(bearingprovider, achieve, order_bearing(Oquant)); // maybe it is better to wait for the response???
								!order(Otype, Oquant, OID).

+!order(Deliverpoint, Otype, Oquant, OID) : stockInventory(N) > Oquant <-
								.send(robota, achieve, at(robota, stock));
								!robotpos;
								deliver_item_robot(Oquant);
								.send(robota, achieve, deliverto(Deliverpoint, Otype, Oquant, OID)).

+!t1: true <- .send(robota, achieve, at(robota, stock));
				!robotpos;
				.print("********robot at stock");
				.send(robota, achieve, deliverto(delivery, 0, 5, 5)).
				
				
+!robotpos : at(robota,stock) <- clearb(2).
//true.
+!robotpos : not at(robota,stock) <- !robotpos.
