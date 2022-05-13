package ru.adm123.springCourse.lesson4.service.business.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.aspect.anno.LogExecuteTime;
import ru.adm123.springCourse.lesson4.model.Question;
import ru.adm123.springCourse.lesson4.model.Test;
import ru.adm123.springCourse.lesson4.model.TestParam;
import ru.adm123.springCourse.lesson4.service.business.ServiceStudentTest;
import ru.adm123.springCourse.lesson4.service.periphery.Printer;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;
import ru.adm123.springCourse.lesson4.util.UtilString;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Реализация интерфейса {@link ServiceStudentTest}, обеспечивающая логику тестирования студента
 */
@Service
public class ServiceStudentTestImpl implements ServiceStudentTest {

    private final ApplicationContext applicationContext;
    private final TestParam testParam;
    private final ServiceBundleResource serviceBundleResourceTestMessageImpl;
    private final ServiceBundleResource serviceBundleResourceQuestionImpl;
    private final Scanner scanner = new Scanner(System.in);
    private final Printer printer;
    private final Map<String, String> testMessageMap = new HashMap<>();

    @Autowired
    public ServiceStudentTestImpl(ApplicationContext applicationContext,
                                  TestParam testParam,
                                  ServiceBundleResource serviceBundleResourceTestMessageImpl,
                                  ServiceBundleResource serviceBundleResourceQuestionImpl,
                                  Printer printer) {
        this.testParam = testParam;
        this.serviceBundleResourceTestMessageImpl = serviceBundleResourceTestMessageImpl;
        this.serviceBundleResourceQuestionImpl = serviceBundleResourceQuestionImpl;
        this.printer = printer;
        this.applicationContext = applicationContext;
        this.testMessageMap.put("studentResult", serviceBundleResourceTestMessageImpl.getString("studentResult"));
        this.testMessageMap.put("testResultOK", serviceBundleResourceTestMessageImpl.getString("testResultOK"));
        this.testMessageMap.put("testResultFAIL", serviceBundleResourceTestMessageImpl.getString("testResultFAIL"));
    }

    @Override
    public Test getNewStudentTest() {
        String studentName = null;
        while (studentName == null) {
            System.out.print(serviceBundleResourceTestMessageImpl.getString("invitation") + ": ");
            studentName = scanner.next();
        }
        String greeting = serviceBundleResourceTestMessageImpl.getString("greeting");
        assert greeting != null;
        System.out.println(String.format(greeting, studentName));
        // Финт, чтобы сработала аннотация на методе getQuestionList()
        List<Question> questions = applicationContext.getBean(getClass()).getQuestionList();
        return new Test(studentName, getOffsetLimit(), testMessageMap, questions);
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

    @Override
    @LogExecuteTime
    public List<Question> getQuestionList() {
        String fileName = testParam.getQuestionList();
        if (!UtilString.hasText(fileName)) {
            throw new RuntimeException("fileNotFound");
        }
        List<Question> questions = new ArrayList<>();
        URL fileURL = getClass().getResource("/" + fileName);
        if (fileURL == null) {
            throw new RuntimeException("fileNotFound");
        }
        try (CSVReader csvReader = new CSVReader(new FileReader(fileURL.getFile()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                if (csvLine.length != 2) {
                    throw new RuntimeException("fileFormatError");
                }
                questions.add(new Question(serviceBundleResourceQuestionImpl.getString(csvLine[0]), csvLine[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    private int getOffsetLimit() {
        return testParam.getOffsetLimit();
    }

    private void proceedQuestion(Test test,
                                 Map.Entry<Question, String> questionEntry) {
        System.out.print(questionEntry.getKey().text() + " ");
        String studentAnswer = scanner.next();
        test.getResult().compute(questionEntry.getKey(), (k, v) -> studentAnswer);
    }

}
