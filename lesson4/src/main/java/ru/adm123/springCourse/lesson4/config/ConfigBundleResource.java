package ru.adm123.springCourse.lesson4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.adm123.springCourse.lesson4.model.TestParam;

@Configuration
public class ConfigBundleResource {

    private final TestParam testParam;

    @Autowired
    public ConfigBundleResource(TestParam testParam) {
        this.testParam = testParam;
    }

    @Bean
    public MessageSource questionMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(testParam.getBundleName());
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public MessageSource testMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18l/testMessages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
