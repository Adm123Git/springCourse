package ru.adm123.springCourse.lesson1.service.periphery;

import ru.adm123.springCourse.lesson1.model.Printable;

import java.io.OutputStream;
import java.util.Collection;

public interface Printer {

    default void printAll(Collection<? extends Printable> printables,
                          OutputStream stream) {
        printables.forEach(printable -> {
            try {
                printable.print(stream);
                stream.write("\n".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    void printAll(Collection<? extends Printable> printables);

}
