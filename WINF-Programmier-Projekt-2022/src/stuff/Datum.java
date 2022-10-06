package stuff;

import java.time.LocalDate;
import java.time.LocalTime;


public class Datum {
    LocalDate datum = LocalDate.now();
    String zeit = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
}
