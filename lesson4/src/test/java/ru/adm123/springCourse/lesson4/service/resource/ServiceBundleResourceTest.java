package ru.adm123.springCourse.lesson4.service.resource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

class ServiceBundleResourceTest {

    private final String KEY_WHICH_EXISTS = "existsKey";
    private final String KEY_WHICH_NOT_EXISTS = "notExistsKey";
    private final Object[] EMPTY_OBJECT_ARR = new Object[]{};
    private final Locale LOCALE_RU = Locale.forLanguageTag("ru");
    private final Locale LOCALE_EN = Locale.forLanguageTag("en");
    private final String MESSAGE_RU = "Найденное сообщение";
    private final String MESSAGE_EN = "Founded message";
    private final String MESSAGE_DEF = "Default locale message";

    private ServiceBundleResource service;
    private final MessageSource questionMessageSource = Mockito.mock(MessageSource.class);

    @BeforeEach
    public void init() {
        service = key -> null;
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_NOT_EXISTS, EMPTY_OBJECT_ARR, Locale.getDefault()))
                .thenThrow(NoSuchMessageException.class);
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_NOT_EXISTS, EMPTY_OBJECT_ARR, LOCALE_RU))
                .thenThrow(NoSuchMessageException.class);
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_NOT_EXISTS, EMPTY_OBJECT_ARR, LOCALE_EN))
                .thenThrow(NoSuchMessageException.class);
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_EXISTS, EMPTY_OBJECT_ARR, Locale.getDefault()))
                .thenReturn(MESSAGE_DEF);
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_EXISTS, EMPTY_OBJECT_ARR, LOCALE_RU))
                .thenReturn(MESSAGE_RU);
        Mockito
                .when(questionMessageSource.getMessage(KEY_WHICH_EXISTS, EMPTY_OBJECT_ARR, LOCALE_EN))
                .thenReturn(MESSAGE_EN);
    }

    @Test
    @DisplayName("Метод getString() с 3 аргументами бросает RuntimeException, если переданный messageSource == null")
    public void getStringWithThreeArg_throwsRTE_ifMessageSourceIsNull() {
        Assertions
                .assertThatThrownBy(() -> service.getString(null, KEY_WHICH_EXISTS, Locale.getDefault()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Метод getString() с 3 аргументами отдает null, если не найдено сообщение в ресурсе")
    public void getStringWithThreeArg_returnsNull_ifMessageIsNotFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_NOT_EXISTS, Locale.getDefault()))
                .isNull();
    }

    @Test
    @DisplayName("Метод getString() с 3 аргументами отдает английскую строку, если найдено сообщение в ресурсе и локаль - en")
    public void getStringWithThreeArg_returnsEngMessage_ifMessageIsFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_EXISTS, LOCALE_EN))
                .isEqualTo(MESSAGE_EN);
    }

    @Test
    @DisplayName("Метод getString() с 3 аргументами отдает русскую строку, если найдено сообщение в ресурсе и локаль - ru")
    public void getStringWithThreeArg_returnsRusMessage_ifMessageIsFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_EXISTS, LOCALE_RU))
                .isEqualTo(MESSAGE_RU);
    }

    @Test
    @DisplayName("Метод getString() с 3 аргументами отдает дефолтную строку, если найдено сообщение в ресурсе и локаль - дефолтная")
    public void getStringWithThreeArg_returnsDefMessage_ifMessageIsFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_EXISTS, Locale.getDefault()))
                .isEqualTo(MESSAGE_DEF);
    }

    @Test
    @DisplayName("Метод getString() с 2 аргументами бросает RuntimeException, если переданный messageSource == null")
    public void getStringWithTwoArg_throwsRTE_ifMessageSourceIsNull() {
        Assertions
                .assertThatThrownBy(() -> service.getString(null, KEY_WHICH_EXISTS))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Метод getString() с 2 аргументами отдает null, если не найдено сообщение в ресурсе")
    public void getStringWithTwoArg_returnsNull_ifMessageIsNotFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_NOT_EXISTS))
                .isNull();
    }

    @Test
    @DisplayName("Метод getString() с 2 аргументами отдает дефолтную строку, если найдено сообщение в ресурсе")
    public void getStringWithTwoArg_returnsDefMessage_ifMessageIsFound() {
        Assertions
                .assertThat(service.getString(questionMessageSource, KEY_WHICH_EXISTS))
                .isEqualTo(MESSAGE_DEF);
    }

}