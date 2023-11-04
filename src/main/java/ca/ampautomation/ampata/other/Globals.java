package ca.ampautomation.ampata.other;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static java.time.format.DateTimeFormatter.*;

public interface Globals {

    String SEP0 = " ";

    String SEP1 = "/";

    String SEP2 = ";";

    String SEP3 = "::";

    String SEP4 = "_";


    String FRMT_SORTKEY = "%02d";

    DateTimeFormatter FRMT_TS = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE_TIME)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

     DateTimeFormatter FRMT_DT = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_DATE)
            .toFormatter();

    DateTimeFormatter FRMT_TM = new DateTimeFormatterBuilder()
            .append(ISO_LOCAL_TIME)
            .toFormatter();

    DecimalFormat FRMT_DEC = new DecimalFormat("+0.00;-0.00");

}
