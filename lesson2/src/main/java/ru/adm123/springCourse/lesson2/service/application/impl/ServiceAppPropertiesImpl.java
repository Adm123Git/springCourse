package ru.adm123.springCourse.lesson2.service.application.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson2.service.application.ServiceAppProperties;

@Getter
@PropertySource("classpath:app.properties")
@Service
public class ServiceAppPropertiesImpl implements ServiceAppProperties {

    @Value("${app.question-csv-file-path}")
    private String questionCsvFilePath;
    @Value("${app.offset-limit}")
    private int offsetLimit;

}
