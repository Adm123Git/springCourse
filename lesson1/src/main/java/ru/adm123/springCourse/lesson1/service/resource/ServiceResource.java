package ru.adm123.springCourse.lesson1.service.resource;

import java.util.List;

public interface ServiceResource<T> {

    List<T> getAll();

}
