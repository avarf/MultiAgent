// Agent robota in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
//+!at(robota1,P) : at(robota1,P) <- .send(P,tell,at(robota1,P)).
//+!at(robota1,P) : not at(robota1,P) <- 
//				move_towards(P);
//				!at(robota1,P).
				
+!at(R,P) : at(R,P) <- .send(P,tell,at(R,P)).
+!at(R,P) : not at(R,P) <- 
				move_towards(P);
				!at(R,P).

+!deliverto(R, Deliverpoint, Otype, Oquant, OID)[source(Agent)] : true <- 
											.send(Agent,untell,at(R,Agent));
											!at(R, Deliverpoint);
											.send(Deliverpoint,tell,at(R,Deliverpoint));
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Destination").
											

