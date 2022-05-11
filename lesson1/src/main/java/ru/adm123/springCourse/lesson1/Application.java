package ru.adm123.springCourse.lesson1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.adm123.springCourse.lesson1.model.Question;
import ru.adm123.springCourse.lesson1.service.periphery.Printer;
import ru.adm123.springCourse.lesson1.service.resource.ServiceResource;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/app-context.xml")) {
            List<Question> questionList = applicationContext
                    .getBean(ServiceResource.class)
                    .getAll();
            applicationContext
                    .getBean(Printer.class)
                    .printAll(questionList);
        }
    }

}
