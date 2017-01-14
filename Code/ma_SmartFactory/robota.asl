// Agent robota in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+!at(R,P) : at(R,P) <- .send(P,tell,at(R,P)).
+!at(R,P) : not at(R,P) <- 
				move_towards(P);
				!at(R,P).
				
+!deliverto(R, Deliverpoint, Otype, Oquant, OID)[source(Agent)] : Deliverpoint = delivery <- 
											.print("+++++++ INSIDE DELIVER PART+++++++");
											.send(Agent,untell,at(R,Agent));
											!at(R, Deliverpoint);
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.send(delivery,achieve,orderdelivered(OID));
											.print(" ********* Reached Destination").
				
+!deliverto(R, Deliverpoint, Otype, Oquant, OID)[source(Agent)] : true <- 
											.send(Agent,untell,at(R,Agent));
											!at(R, Deliverpoint);
											.send(Deliverpoint,tell,at(R,Deliverpoint));
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Destination").
