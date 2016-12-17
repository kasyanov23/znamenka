package ru.click.cabinet.config.formatter;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.parse;

/**
 * <p>
 * Создан 11.10.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public LocalDateTime convert(String s) {
        return parse(s, formatter);
    }
}
