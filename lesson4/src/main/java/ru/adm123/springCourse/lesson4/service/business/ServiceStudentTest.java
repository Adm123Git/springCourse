package ru.adm123.springCourse.lesson4.service.business;

import ru.adm123.springCourse.lesson4.model.Question;
import ru.adm123.springCourse.lesson4.model.Test;

import java.util.List;

/**
 * Процесс тестирования
 */
public interface ServiceStudentTest {

    /**
     * Формирует новый объект {@link Test}
     *
     * @param questions   коллекция объектов {@link Question}, которые будут "участниками" теста
     * @param offsetLimit минимальное количество верных ответов, необходимое для зачета
     * @return готовый объект {@link Test}
     */
    Test getNewStudentTest(List<Question> questions,
                           int offsetLimit);

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
