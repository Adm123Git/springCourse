package ru.adm123.springCourse.lesson1.model;

import java.io.IOException;
import java.io.OutputStream;

public record Question(String text,
                       String rightAnswer) implements Printable {
    @Override
    public void print(OutputStream stream) throws IOException {
        String output = "Question: " + text + ", Answer: " + rightAnswer;
        stream.write(output.getBytes());
        stream.flush();
    }

}
