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

    /**
     * Числовой код студии
     */
    private final int code;

    Studio(int code) {
        this.code = code;
    }

    /**
     * Геттер кода студии
     * @return код студии
     */
    public int code() {
        return this.code;
    }

    /**
     * Возвращает студию по ее коду
     * @param code код студии
     * @return студия
     */
    public static Studio from(int code) {
        return stream(Studio.values())
                .filter(st -> st.code() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
