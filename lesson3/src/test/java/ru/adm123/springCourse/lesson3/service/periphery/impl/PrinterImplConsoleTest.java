package ru.adm123.springCourse.lesson3.service.periphery.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import ru.adm123.springCourse.lesson3.model.Printable;
import ru.adm123.springCourse.lesson3.model.Question;
import ru.adm123.springCourse.lesson3.service.periphery.Printer;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

class PrinterImplConsoleTest {

    private static final OutputStream stream = new ByteArrayOutputStream();
    private Collection<Printable> printableList;
    private Printer service;

    private final Question question1 = new Question("question1", "answer1");
    private final Question question2 = new Question("question2", "answer2");
    private final Question question3 = new Question("question3", "answer3");

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(stream));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(System.out);
    }

    @BeforeEach
    public void initList() {
        service = new PrinterImplConsole();
        printableList = Arrays.asList(question1, question2, question3);
    }

    @Test
    @DisplayName("printAll() выводит все Printable-сущности из коллекции, переданной на вход")
    void printAll_printsAllPrintableObjectsFromList() {
        service.printAll(printableList);
        Assertions
                .assertThat(stream.toString())
                .contains(question1.text())
                .contains(question1.rightAnswer())
                .contains(question2.text())
                .contains(question2.rightAnswer())
                .contains(question3.text())
                .contains(question3.rightAnswer());
    }

}