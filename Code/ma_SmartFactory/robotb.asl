// Agent robotb is just for transfering assemby trays between tray stock and assembly machine
/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+!at(robotb, P) : at(robotb, P) <- true.
+!at(robotb, P) : not at(robotb, P) <- 
				move_towards(P);
				!at(robotb, P).

+!deliverto(Deliverpoint, Otype, Oquant, OID) : true <- 
											!at(robotb, Deliverpoint);
											deliver_payload(Deliverpoint, Otype, Oquant, OID).
