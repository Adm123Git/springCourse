package ru.adm123.springCourse.lesson4.service.resource;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;

import java.util.Locale;

/**
 * Работа с ресурсами, объединенными в Bundle-ы
 */
public interface ServiceBundleResource {

    /**
     * Возвращает строку по ключу из {@link MessageSource}, который использует реализация, с использованием дефолтной локали
     *
     * @param key ключ
     * @return {@link String} значение
     */
    @Nullable
    String getString(String key);

    /**
     * Возвращает строку по ключу из заданного {@link MessageSource} с использованием дефолтной локали
     *
     * @param messageSource {@link MessageSource}, в котором ищется значение
     * @param key           ключ
     * @return {@link String} значение
     */
    @Nullable
    default String getString(MessageSource messageSource,
                             String key) {
        return getString(messageSource, key, Locale.getDefault());
    }

    /**
     * Возвращает строку по ключу из заданного {@link MessageSource} с использованием заданной локали
     *
     * @param messageSource {@link MessageSource}, в котором ищется значение
     * @param key           ключ
     * @param locale        {@link Locale} нужная локаль
     * @return {@link String} значение
     */
    @Nullable
    default String getString(MessageSource messageSource,
                             String key,
                             Locale locale) {
        if (messageSource == null) {
            throw new RuntimeException("messageSource is null");
        }
        try {
            return messageSource.getMessage(key, new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            return null;
        }
    }

}
