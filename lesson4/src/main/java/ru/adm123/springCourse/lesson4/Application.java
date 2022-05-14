package ru.adm123.springCourse.lesson4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.adm123.springCourse.lesson4.model.Test;
import ru.adm123.springCourse.lesson4.model.TestParam;
import ru.adm123.springCourse.lesson4.service.business.ServiceStudentTest;
import ru.adm123.springCourse.lesson4.service.data.impl.ServiceDataQuestion;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ServiceDataQuestion serviceDataQuestion = applicationContext.getBean(ServiceDataQuestion.class);
        ServiceStudentTest serviceStudentTest = applicationContext.getBean(ServiceStudentTest.class);
        TestParam testParam = applicationContext.getBean(TestParam.class);
        Test test = serviceStudentTest.getNewStudentTest(serviceDataQuestion.getAll(), testParam.getOffsetLimit());
        while (!test.isTestFinished()) {
            serviceStudentTest.showNextQuestion(test);
        }
        serviceStudentTest.printResult(test);

    }

}
