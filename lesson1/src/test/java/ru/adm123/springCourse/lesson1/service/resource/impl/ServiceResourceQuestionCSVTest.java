package ru.adm123.springCourse.lesson1.service.resource.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.adm123.springCourse.lesson1.model.Question;
import ru.adm123.springCourse.lesson1.service.resource.ServiceResource;

class ServiceResourceQuestionCSVTest {

    private ServiceResource<Question> service;

    public void initService(String fileName) {
        this.service = new ServiceResourceQuestionCSV(fileName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "sfsfdsf.csv"})
    @DisplayName("getAll() бросает RuntimeException(), если имя файла пустое или файл не найден")
    void getAll_throwsRuntimeException_ifFileNameIsEmptyOrFileNotExists(String fileName) {
        initService(fileName);
        Assertions
                .assertThatThrownBy(() -> service.getAll())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("getAll() бросает RuntimeException(), если имя файла == null")
    void getAll_throwsRuntimeException_ifFileNameIsNull() {
        initService(null);
        Assertions
                .assertThatThrownBy(() -> service.getAll())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("getAll() отдает список объектов Question, если файл найден и его содержимое корректно")
    void getAll_returnsList_ifAllOk() {
        initService("questions_5_correct.csv");
        Assertions
                .assertThat(service.getAll())
                .asList()
                .hasSize(5)
                .allMatch(item -> item instanceof Question);
    }

    @Test
    @DisplayName("getAll() бросает RuntimeException(), если файл найден? но его содержимое некорректно")
    void getAll_throwsRuntimeException_ifFileContentsIsIncorrect() {
        initService("questions_5_correct.csv");
        Assertions
                .assertThat(service.getAll())
                .asList()
                .hasSize(5)
                .allMatch(item -> item instanceof Question);
    }

}