package ru.adm123.springCourse.lesson2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.adm123.springCourse.lesson2.model.Question;
import ru.adm123.springCourse.lesson2.model.Test;
import ru.adm123.springCourse.lesson2.service.business.ServiceStudentTest;
import ru.adm123.springCourse.lesson2.service.resource.ServiceResource;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class)) {
            ServiceStudentTest serviceStudentTest = applicationContext.getBean(ServiceStudentTest.class);
            ServiceResource<Question> serviceResourceQuestion = applicationContext.getBean(ServiceResource.class);
            Test test = serviceStudentTest.getNewStudentTest(serviceResourceQuestion.getAll());
            while (!test.isTestFinished()) {
                serviceStudentTest.showNextQuestion(test);
            }
            serviceStudentTest.printResult(test);
        }
    }

}
