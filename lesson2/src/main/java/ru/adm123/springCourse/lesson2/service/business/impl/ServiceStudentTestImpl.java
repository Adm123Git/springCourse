package ru.adm123.springCourse.lesson2.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson2.model.Question;
import ru.adm123.springCourse.lesson2.model.Test;
import ru.adm123.springCourse.lesson2.service.application.ServiceAppProperties;
import ru.adm123.springCourse.lesson2.service.business.ServiceStudentTest;
import ru.adm123.springCourse.lesson2.service.periphery.Printer;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * Реализация интерфейса {@link ServiceStudentTest}, обеспечивающая логику тестирования студента
 */
@Service
public class ServiceStudentTestImpl implements ServiceStudentTest {

    private final ServiceAppProperties serviceAppProperties;
    private final Scanner scanner = new Scanner(System.in);
    private final Printer printer;


    @Autowired
    public ServiceStudentTestImpl(ServiceAppProperties serviceAppProperties,
                                  Printer printer) {
        this.serviceAppProperties = serviceAppProperties;
        this.printer = printer;
    }

    @Override
    public Test getNewStudentTest(Collection<Question> questions) {
        String studentName = null;
        while (studentName == null) {
            System.out.print("Type your name: ");
            studentName = scanner.next();
        }
        System.out.println("Hi, " + studentName + "! Let's start...");
        return new Test(studentName, serviceAppProperties.getOffsetLimit(), questions);
    }

    @Override
    public void showNextQuestion(Test test) {
        test.getResult().entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .findFirst()
                .ifPresent(questionEntry -> proceedQuestion(test, questionEntry));
    }

    @Override
    public void printResult(Test test) {
        printer.print(test);
    }

    private void proceedQuestion(Test test,
                                 Map.Entry<Question, String> questionEntry) {
        System.out.print(questionEntry.getKey().text() + " ");
        String studentAnswer = scanner.next();
        test.getResult().compute(questionEntry.getKey(), (k, v) -> studentAnswer);
    }

}
