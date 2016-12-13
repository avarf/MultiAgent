// Agent customer in project DomesticRobot.mas2j
/* Initial beliefs and rules */

/* Initial goals */
!start.

/* Plans */
// we use 0 for bearing and 1 for assembeled bearings
// order(type, quantity, orderID)
// +!start : true <- .generateorder().
+!start : true <- .send(manager, achieve, order(1,5,1 ));
					.print("Order sent").
					
+!assembled : true <- .send(manager, achieve, order(1,5,1 ));
					.print("Order sent").
