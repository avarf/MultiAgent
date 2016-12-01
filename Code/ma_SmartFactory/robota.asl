// Agent robota in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */
//!at(robota,delivery).
//!testgoal.
//!testgoal2.
//!deliverto(stock, 0, 11,10).
hello(test).
/* Plans */
+!testgoal : true <- !at(robota,stock);
					 !at(robota,delivery);
					 .print(" ********* Reached Stock").
					 
+!testgoal2 : at(robota,P) <- .send(stock,tell,at(robota,P)).
+!testgoal2 : not at(robota,P) <- !testgoal2.

+!at(robota,P) : at(robota,P) <- !testgoal2.
//true.
+!at(robota,P) : not at(robota,P) <- 
				move_towards(P);
				!at(robota,P).

//+!at(robota,P) : at(robota,stock) <- .print("asjhdhjhashd").
//+at(robota,stock) : true <- .print("After Checking The Position.").
//+!at(robota,P) : at(robota,P) <- true.
				
+!deliverto(Deliverpoint, Otype, Oquant, OID) : true <- 
											!at(robota, Deliverpoint);
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Destination").
											
											
										
