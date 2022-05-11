package ru.adm123.springCourse.lesson3.service.business;

import ru.adm123.springCourse.lesson3.model.Question;
import ru.adm123.springCourse.lesson3.model.Test;

import java.util.Collection;

/**
 * Процесс тестирования
 */
public interface ServiceStudentTest {

    /**
     * Формирует новый объект {@link Test} на основании переданной коллекции объектов {@link Question}
     *
     * @param questions коллекция объектов {@link Question}, которые будут "участниками" теста
     * @return готовый объект {@link Test}
     */
    Test getNewStudentTest(Collection<Question> questions);

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
