package ru.click.cabinet.config.formatter;


import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatterFC = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (text == null || text.isEmpty()) return null;
        try {
            return Date.valueOf(LocalDate.parse(text, formatter));
        } catch (DateTimeParseException e) {
            return Date.valueOf(LocalDate.parse(text, formatterFC));
        }
    }

    @Override
    public String print(Date date, Locale locale) {
        return formatter.format(date.toLocalDate());
    }
}
