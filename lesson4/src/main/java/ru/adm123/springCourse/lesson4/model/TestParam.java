package ru.adm123.springCourse.lesson4.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "test")
public class TestParam {

    private String bundleName;
    private String questionList;
    private int offsetLimit;

}