package ru.adm123.springCourse.lesson4.config.appStart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartInfo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Приложение запущено");
    }

}
