package ru.click.cabinet.repository.query;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

import static java.util.stream.Collectors.joining;

/**
 * Абстрактный класс, который подгружает из файлов
 * и держит в памяти sql запросы
 * <p>
 * Создан 14.11.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public abstract class QueriesHolder {

    public final static String clientTrainings = queryLoader("sql/last_60_client_trainings.sql");

    public final static String balanceOfTraining = queryLoader("sql/training-count.sql");

    private static String queryLoader(String path) {
        Resource res = new ClassPathResource(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            return reader.lines().collect(joining(" "));
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

}
