// Agent delivery in project DomesticRobot.mas2j



/* Initial beliefs and rules */



/* Initial goals */



/* Plans */
+!orderdelivered(OID) : true <- .send(manager, achieve, orderfinished(OID)).

