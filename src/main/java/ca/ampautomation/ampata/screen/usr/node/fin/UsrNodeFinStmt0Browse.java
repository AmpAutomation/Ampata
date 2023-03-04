package ca.ampautomation.ampata.screen.usr.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrNodeFinStmt.browse")
@UiDescriptor("usr-node-fin-stmt-0-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeFinStmt0Browse extends StandardLookup<UsrNodeBase> {
    @Install(to = "tableMain.[n1s1Inst1Ts1.elTs]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}