package ru.adm123.springCourse.lesson4.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.model.Question;
import ru.adm123.springCourse.lesson4.model.Test;
import ru.adm123.springCourse.lesson4.service.business.ServiceStudentTest;
import ru.adm123.springCourse.lesson4.service.periphery.Printer;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Реализация интерфейса {@link ServiceStudentTest}, обеспечивающая логику тестирования студента
 */
@Service
public class ServiceStudentTestImpl implements ServiceStudentTest {

    private final ServiceBundleResource serviceBundleResourceTestMessageImpl;
    private final Scanner scanner = new Scanner(System.in);
    private final Printer printer;
    private final Map<String, String> testMessageMap = new HashMap<>();

    @Autowired
    public ServiceStudentTestImpl(ServiceBundleResource serviceBundleResourceTestMessageImpl,
                                  Printer printer) {
        this.serviceBundleResourceTestMessageImpl = serviceBundleResourceTestMessageImpl;
        this.printer = printer;
        this.testMessageMap.put("studentResult", serviceBundleResourceTestMessageImpl.getString("studentResult"));
        this.testMessageMap.put("testResultOK", serviceBundleResourceTestMessageImpl.getString("testResultOK"));
        this.testMessageMap.put("testResultFAIL", serviceBundleResourceTestMessageImpl.getString("testResultFAIL"));
    }

    @Override
    public Test getNewStudentTest(List<Question> questions,
                                  int offsetLimit) {
        String studentName = null;
        while (studentName == null) {
            System.out.print(serviceBundleResourceTestMessageImpl.getString("invitation") + ": ");
            studentName = scanner.next();
        }
        String greeting = serviceBundleResourceTestMessageImpl.getString("greeting");
        assert greeting != null;
        System.out.println(String.format(greeting, studentName));
        return new Test(studentName, offsetLimit, testMessageMap, questions);
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
