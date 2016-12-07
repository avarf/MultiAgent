// Agent robota in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!at(robota,P) : at(robota,P) <- .send(P,tell,at(robota,P)).
+!at(robota,P) : not at(robota,P) <- 
				move_towards(P);
				!at(robota,P).

+!deliverto(Deliverpoint, Otype, Oquant, OID)[source(Agent)] : true <- 
											.send(Agent,untell,at(robota,Agent));
											!at(robota, Deliverpoint);
											.send(Deliverpoint,tell,at(robota,Deliverpoint));
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Destination").
											

