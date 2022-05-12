package ru.adm123.springCourse.lesson4.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;

/**
 * Реализация интерфейса {@link ServiceBundleResource}, обеспечивающая работу с bundle-ом вопросов к тесту
 */
@Service
public class ServiceBundleResourceQuestionImpl implements ServiceBundleResource {

    private final MessageSource questionMessageSource;

    @Autowired
    public ServiceBundleResourceQuestionImpl(MessageSource questionMessageSource) {
        this.questionMessageSource = questionMessageSource;
    }

    @Override
    public String getString(String key) {
        return getString(questionMessageSource, key);
    }

}
