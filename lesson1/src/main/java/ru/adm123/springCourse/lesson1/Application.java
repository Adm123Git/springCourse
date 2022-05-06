package ru.adm123.springCourse.lesson1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.adm123.springCourse.lesson1.model.Question;
import ru.adm123.springCourse.lesson1.service.periphery.impl.PrinterImplConsole;
import ru.adm123.springCourse.lesson1.service.resource.impl.ServiceResourceQuestionCSV;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-context.xml");
        List<Question> questionList = applicationContext.getBean(ServiceResourceQuestionCSV.class).getAll();
        applicationContext.getBean(PrinterImplConsole.class).printAll(questionList);
    }

}
