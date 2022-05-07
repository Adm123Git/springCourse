package ru.adm123.springCourse.lesson2.service.periphery;

import ru.adm123.springCourse.lesson2.model.Printable;

import java.io.OutputStream;
import java.util.Collection;

/**
 * Обеспечивает вывод данных из приложения куда-нибудь вовне
 */
public interface Printer {

    /**
     * Выводит построчно коллекцию объектов
     *
     * @param printables коллекция {@link Printable}-объектов
     * @param stream     {@link OutputStream}, в который осуществляется вывод
     */
    default void printAll(Collection<? extends Printable> printables,
                          OutputStream stream) {
        printables.forEach(printable -> print(printable, stream));
    }

    /**
     * Выводит коллекцию объектов
     *
     * @param printables коллекция {@link Printable}-объектов
     */
    void printAll(Collection<? extends Printable> printables);

    /**
     * Выводит объект
     *
     * @param printable {@link Printable}-объект
     * @param stream    {@link OutputStream}, в который осуществляется вывод
     */
    default void print(Printable printable,
                       OutputStream stream) {
        try {
            printable.print(stream);
            stream.write("\n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Выводит объект
     *
     * @param printable {@link Printable}-объект
     */
    void print(Printable printable);

}
