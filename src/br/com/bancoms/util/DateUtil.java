package br.com.bancoms.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public static final Locale local = new Locale("pt", "BR");

    public static String parseDate(Timestamp data) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", local).format(data);
    }

    public static Timestamp getCurrentDate() {
        return new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime().getTime());
    }

    private static String parseDefaultUSA(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", local).format(calendar.getTime().getTime());
    }

    private static Calendar parseInCalendar(int ano, int mes, int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        calendar.set(ano, mes, dia, 00, 00, 0);
        return calendar;
    }

    public static String getDate(int ano, int mes, int dia) {
        return parseDefaultUSA(parseInCalendar(ano, mes, dia));
    }

}
