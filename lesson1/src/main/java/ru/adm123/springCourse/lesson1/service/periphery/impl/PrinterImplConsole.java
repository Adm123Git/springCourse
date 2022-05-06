package ru.adm123.springCourse.lesson1.service.periphery.impl;

import ru.adm123.springCourse.lesson1.model.Printable;
import ru.adm123.springCourse.lesson1.service.periphery.Printer;

import java.util.Collection;

/**
 * Реализация интерфейса {@link Printer}, обеспечивающая вывод в консоль
 */
public class PrinterImplConsole implements Printer {

    @Override
    public void printAll(Collection<? extends Printable> printables) {
        printAll(printables, System.out);
    }

}
