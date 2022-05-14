package ru.adm123.springCourse.lesson4.service.data;

import java.util.List;

/**
 * Обеспечивает работу с данными
 *
 * @param <T> тип данных, с которыми работает сервис
 */
public interface ServiceData<T> {

    /**
     * Получение всех объектов, которые может предоставить сервис
     *
     * @return {@link List} объектов типа {@code <T>}
     */
    List<T> getAll();

}
