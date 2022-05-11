package ru.adm123.springCourse.lesson3.aspect.anno;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecuteTime {

    TimeUnit value() default TimeUnit.MILLIS;

    enum TimeUnit {

        SECONDS("s"),
        MILLIS("ms");

        @Getter
        private final String unitName;

        TimeUnit(String unitName) {
            this.unitName = unitName;
        }

    }

}
