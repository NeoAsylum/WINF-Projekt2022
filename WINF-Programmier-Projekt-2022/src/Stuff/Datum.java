package Stuff;

import java.time.LocalDate;
import java.time.LocalTime;

import Annotations.ZuEditieren;

@ZuEditieren(mussUeberarbeitetWerden = true, zugewiesen = "Adrian")
public class Datum {
    LocalDate datum = LocalDate.now();
    String zeit = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
}
