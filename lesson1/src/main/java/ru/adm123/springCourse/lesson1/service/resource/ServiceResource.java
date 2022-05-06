package ru.adm123.springCourse.lesson1.service.resource;

import java.util.List;

/**
 * Обеспечивает работу с ресурсами
 *
 * @param <T> тип объектов, которые предоставляет ресурс
 */
public interface ServiceResource<T> {

    /**
     * Получение всех объектов, которые может предоставить ресурс
     *
     * @return {@link List} объектов типа {@code <T>}
     */
    List<T> getAll();

}
