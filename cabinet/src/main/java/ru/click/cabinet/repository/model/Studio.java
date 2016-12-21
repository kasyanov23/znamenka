package ru.click.cabinet.repository.model;

import static java.util.Arrays.stream;

/**
 * Перечисление студий
 * <p>
 * Создан 21.12.2016
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
public enum Studio {

    ZNAMENKA(1);

    private final int code;

    Studio(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

    public static Studio from(int code) {
        return stream(Studio.values())
                .filter(st -> st.code() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
