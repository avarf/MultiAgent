// Agent customer in project DomesticRobot.mas2j
/* Initial beliefs and rules */
ttl[].
/* Initial goals */
//!bearing.
!assembled.
//!test.

/* Plans */
// we use 0 for bearing and 1 for assembeled bearings
// order(type, quantity, orderID)
// +!start : true <- .generateorder().
+!bearing : true <- .send(manager, achieve, order(0,5,1 ));
					.print("Order sent").
					
+!assembled : true <- .send(manager, achieve, order(1,5,1 ));
						.send(manager, achieve, order(0,5,2 ));
						.send(manager, achieve, order(1,5,3 ));
					.print("Order sent").
