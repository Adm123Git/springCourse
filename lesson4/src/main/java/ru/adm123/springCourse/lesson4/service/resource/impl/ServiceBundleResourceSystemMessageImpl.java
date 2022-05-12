package ru.adm123.springCourse.lesson4.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;

/**
 * Реализация интерфейса {@link ServiceBundleResource}, обеспечивающая работу с bundle-ом системных сообщений
 */
@Service
public class ServiceBundleResourceSystemMessageImpl implements ServiceBundleResource {

    private final MessageSource systemMessageSource;

    @Autowired
    public ServiceBundleResourceSystemMessageImpl(MessageSource systemMessageSource) {
        this.systemMessageSource = systemMessageSource;
    }

    @Override
    public String getString(String key) {
        return getString(systemMessageSource, key);
    }

}
