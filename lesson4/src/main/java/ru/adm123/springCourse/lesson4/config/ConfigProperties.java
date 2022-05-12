package ru.adm123.springCourse.lesson4.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.adm123.springCourse.lesson4.model.TestParam;

@Configuration
@EnableConfigurationProperties(TestParam.class)
public class ConfigProperties {
}
