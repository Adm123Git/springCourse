package ru.adm123.springCourse.lesson4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.adm123.springCourse.lesson4.model.Test;
import ru.adm123.springCourse.lesson4.service.business.ServiceStudentTest;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ServiceStudentTest serviceStudentTest = applicationContext.getBean(ServiceStudentTest.class);
        Test test = serviceStudentTest.getNewStudentTest();
        while (!test.isTestFinished()) {
            serviceStudentTest.showNextQuestion(test);
        }
        serviceStudentTest.printResult(test);

    }

}
