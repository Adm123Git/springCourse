package ru.adm123.springCourse.lesson4.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;

/**
 * Реализация интерфейса {@link ServiceBundleResource}, обеспечивающая работу с bundle-ом сообщений, используемых при тестировании
 */
@Service
public class ServiceBundleResourceTestMessageImpl implements ServiceBundleResource {

    private final MessageSource testMessageSource;

    @Autowired
    public ServiceBundleResourceTestMessageImpl(MessageSource testMessageSource) {
        this.testMessageSource = testMessageSource;
    }

    @Override
    public String getString(String key) {
        return getString(testMessageSource, key);
    }

}
