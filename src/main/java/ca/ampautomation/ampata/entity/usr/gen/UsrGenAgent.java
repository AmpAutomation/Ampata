package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

@JmixEntity
@Entity(name = "enty_UsrGenAgent")
public class UsrGenAgent extends UsrNode {

    @Override
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        final String SEP = "/";
        final String SEP2 = ";";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");

        boolean isChanged = false;
        String desc1_ = this.getDesc1();

        String nm = this.getName2() != null ? this.getName2()
                : this.getName1() != null ? this.getName1()
                : "";

        switch (this.getType1_Id2()){
            case "/C" -> {
                String a1fn = this.getGenAgent1_Id() == null ? "" : String.valueOf(this.getGenAgent1_Id().getNameFrst());
                String a1ln = this.getGenAgent1_Id() == null ? "" : String.valueOf(this.getGenAgent1_Id().getNameLast());
                String a2fn = this.getGenAgent2_Id() == null ? "" : String.valueOf(this.getGenAgent2_Id().getNameFrst());
                String a2ln = this.getGenAgent2_Id() == null ? "" : String.valueOf(this.getGenAgent2_Id().getNameLast());

                sb.append(a1fn);
                sb.append(a1ln.equals(nm) ? "" : " (" + a1ln + ")");
                sb.append(" & ");
                sb.append(a2fn);
                sb.append(a2ln.equals(nm) ? "" : " (" + a2ln + ")");
                sb.append(" ");
                sb.append(nm);
            }
            default -> {
                sb.append(nm);
            }
        }

        String tag = "";
        String tag1 = "";
        if (this.getGenTag1_Id() != null) {
            tag1 = Objects.toString(this.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (this.getGenTag1_Id() != null) {
            tag2 = Objects.toString(this.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (this.getGenTag1_Id() != null) {
            tag3 = Objects.toString(this.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (this.getGenTag1_Id() != null) {
            tag4 = Objects.toString(this.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);

        sb.append(tag);
        String desc1 = sb.toString();
/*
        List<String> list = Arrays.asList(
                name1
                ,tag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
*/

        if (!Objects.equals(desc1_, desc1)){
            this.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}