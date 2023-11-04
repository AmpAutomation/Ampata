package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;

import java.time.LocalDateTime;

public record UsrNodeFinTxactItmGrpg(
         UsrNodeBase parent1_Id
        ,LocalDateTime ts1
        ,Integer int1
        ,Integer int2
    ) {
}
