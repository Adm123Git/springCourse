package ru.adm123.springCourse.lesson3.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Обеспечивает вывод объекта куда-нибудь вовне
 */
public interface Printable {

    /**
     * Вывод объекта, реализующего данный интерфейс, в поток вывода
     *
     * @param stream {@link OutputStream}, в котрый идет вывод
     * @throws IOException при ошибке записи в поток вывода
     */
    void print(OutputStream stream) throws IOException;

}
