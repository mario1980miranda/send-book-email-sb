package com.code.truck.sendbookemailsb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class GenerateBookReturnDate {

    public static int numDaysToReturnBook = 7;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDate(final Date loanDate) {
        Calendar calendar = convertDateToCalendar(loanDate);
        calendar.add(Calendar.DATE, numDaysToReturnBook);
        return dateFormat.format(convertCalendarToDate(calendar));
    }

    private static Date convertCalendarToDate(final Calendar calendar) {
        return calendar.getTime();
    }

    private static Calendar convertDateToCalendar(final Date loanDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanDate);
        return calendar;
    }
}
