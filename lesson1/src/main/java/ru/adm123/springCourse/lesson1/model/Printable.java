package ru.adm123.springCourse.lesson1.model;

import java.io.IOException;
import java.io.OutputStream;

public interface Printable {

    void print(OutputStream stream) throws IOException;

}
