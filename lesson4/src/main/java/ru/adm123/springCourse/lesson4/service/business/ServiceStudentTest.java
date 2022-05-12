package ru.adm123.springCourse.lesson4.service.business;

import ru.adm123.springCourse.lesson4.model.Test;

/**
 * Процесс тестирования
 */
public interface ServiceStudentTest {

    /**
     * Формирует новый объект {@link Test}
     *
     * @return готовый объект {@link Test}
     */
    Test getNewStudentTest();

    /**
     * Показывает и обрабатывает ответ на очередной вопрос теста
     *
     * @param test объект {@link Test}, по которому идет тестирование
     */
    void showNextQuestion(Test test);

    /**
     * Вывод результатов тестирования
     *
     * @param test объект {@link Test}, результаты которого выводятся
     */
    void printResult(Test test);

}
