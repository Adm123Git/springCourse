package ru.adm123.springCourse.lesson4.service.resource.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;

import java.util.Locale;

class ServiceBundleResourceTestMessageImplTest {

    private final String KEY_WHICH_EXISTS = "existsKey";
    private final String KEY_WHICH_NOT_EXISTS = "notExistsKey";
    private final Object[] EMPTY_OBJECT_ARR = new Object[]{};
    private final String MESSAGE_DEF = "Default locale message";

    private ServiceBundleResource serviceBundleResourceTestMessageImpl;
    private final MessageSource messageSource = Mockito.mock(MessageSource.class);

    @BeforeEach
    public void init() {
        serviceBundleResourceTestMessageImpl = new ServiceBundleResourceTestMessageImpl(messageSource);
        Mockito
                .when(messageSource.getMessage(KEY_WHICH_EXISTS, EMPTY_OBJECT_ARR, Locale.getDefault()))
                .thenReturn(MESSAGE_DEF);
    }

    @Test
    @DisplayName("getString() бросает RuntimeException, если в сервис передан messageSource == null")
    public void getString_throwsRTE_ifMessageSourceIsNull() {
        Assertions
                .assertThatThrownBy(() -> new ServiceBundleResourceTestMessageImpl(null).getString(KEY_WHICH_EXISTS))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("getString() возвращает null, если не найдено сообщение в ресурсе")
    public void getString_returnsNull_ifMessageIsNotFound() {
        Assertions
                .assertThat(serviceBundleResourceTestMessageImpl.getString(KEY_WHICH_NOT_EXISTS))
                .isNull();
    }


    @Test
    @DisplayName("getString() возвращает дефолтную строку, если найдено сообщение в ресурсе")
    public void getString_returnsDefMessage_ifMessageIsFound() {
        Assertions
                .assertThat(serviceBundleResourceTestMessageImpl.getString(KEY_WHICH_EXISTS))
                .isEqualTo(MESSAGE_DEF);
    }

}