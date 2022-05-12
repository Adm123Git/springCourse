package ru.adm123.springCourse.lesson4.service.periphery.impl;

import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.model.Printable;
import ru.adm123.springCourse.lesson4.service.periphery.Printer;

import java.util.Collection;

/**
 * Реализация интерфейса {@link Printer}, обеспечивающая вывод в консоль
 */
@Service
public class PrinterImplConsole implements Printer {

    @Override
    public void printAll(Collection<? extends Printable> printables) {
        printAll(printables, System.out);
    }

    @Override
    public void print(Printable printable) {
        print(printable, System.out);
    }

}
