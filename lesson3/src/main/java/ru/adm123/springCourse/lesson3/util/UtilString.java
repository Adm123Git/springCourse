package ru.adm123.springCourse.lesson3.util;

/**
 * Набор утилит для работы со строками
 */
public class UtilString {

    private UtilString() {
    }

    /**
     * Проверяет, содержит ли строка текст, отличный от пробелов
     *
     * @param str проверяемая строка
     * @return {@code false}, если строка является {@code null} или содержит только пробельные символы, иначе - {@code true}
     */
    public static boolean hasText(String str) {
        return str != null && str.trim().length() > 0;
    }

}
