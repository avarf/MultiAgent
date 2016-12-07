// Agent robotb is just for transfering assemby trays between tray stock and assembly machine
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+!at(robotb,P) : at(robotb,P) <- .send(P,tell,at(robotb,P)).
+!at(robotb,P) : not at(robotb,P) <- 
				move_towards(P);
				!at(robotb,P).

+!deliverto(Deliverpoint, Otype, Oquant, OID)[source(Agent)] : true <- 
											.send(Agent,untell,at(robotb,Agent));
											!at(robotb, Deliverpoint);
											.send(Deliverpoint,tell,at(robotb,Deliverpoint));
											deliver_payload(Deliverpoint, Otype, Oquant, OID);
											.print(" ********* Reached Destination").

