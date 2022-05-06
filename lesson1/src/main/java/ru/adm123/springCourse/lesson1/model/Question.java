package ru.adm123.springCourse.lesson1.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Объект-запись, описывающий вопрос и правильный ответ на него
 *
 * @param text        текст вопроса
 * @param rightAnswer правильный ответ
 */
public record Question(String text,
                       String rightAnswer) implements Printable {

    @Override
    public void print(OutputStream stream) throws IOException {
        String output = "Question: " + text + ", Answer: " + rightAnswer;
        stream.write(output.getBytes());
        stream.flush();
    }

}
