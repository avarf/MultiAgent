// Agent robota in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */
//!at(robota,machine).
//!testgoal.
!deliverto(machine, 0, 11,10).

/* Plans */
+!testgoal : true <- !at(robota,stock);
					.print(" ********* Reached Stock").

+!at(robota,P) : at(robota,P) <- true.
+!at(robota,P) : not at(robota,P) <- 
				move_towards(P);
				!at(robota,P).
//+!at(robota,P) : at(robota,stock) <- .print("asjhdhjhashd").
//+at(robota,stock) : true <- .print("After Checking The Position.").
//+!at(robota,P) : at(robota,P) <- true.
				
+!deliverto(Deliverpoint, Otype, Oquant, OID) : true <- 
											!at(robota, Deliverpoint);
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Stock").
