package ru.adm123.springCourse.lesson4.model;

import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс, описывающий результат тестирования студента
 */
@Getter
public class Test implements Printable {

    @NonNull
    private final String studentName;
    private final int offsetLimit;
    private final Map<String, String> messageMap = new HashMap<>();
    private final Map<Question, String> result = new HashMap<>();

    public Test(String studentName,
                int offsetLimit,
                Map<String, String> messageMap,
                Collection<Question> questions) {
        this.studentName = studentName;
        this.offsetLimit = offsetLimit;
        this.messageMap.putAll(messageMap);
        questions.forEach(question -> result.put(question, null));
    }

    @Override
    public void print(OutputStream stream) throws IOException {
        String result = String.format(messageMap.get("studentResult"), studentName, getRightAnswerCount(), getQuestionCount())
                + "\n"
                + messageMap.get(isTestCompetedSuccess() ? "testResultOK" : "testResultFAIL");
        stream.write(result.getBytes());
    }

    /**
     * Возвращает количество правильных ответов
     *
     * @return количество правильных ответов на заданные вопросы
     */
    public long getRightAnswerCount() {
        return result.entrySet().stream()
                .filter(entry -> entry.getKey().rightAnswer().equals(entry.getValue()))
                .count();
    }

    /**
     * Возвращает количество заданных вопросов
     *
     * @return количество заданных вопросов
     */
    public long getQuestionCount() {
        return result.values().stream()
                .filter(Objects::nonNull)
                .count();
    }

    /**
     * Возвращает результат тестирования - зачет/незачет
     *
     * @return {@code true}, если зачет, иначе {@code false}
     */
    public boolean isTestCompetedSuccess() {
        return isTestFinished()
                && getRightAnswerCount() >= offsetLimit;
    }

    /**
     * Возвращает флаг - закончен ли тест (т.е. все ли вопросы заданы и получили ответы)
     *
     * @return {@code true}, если тест закончен, иначе - {@code false}
     */
    public boolean isTestFinished() {
        return result.size() > 0
                && getQuestionCount() == result.size();
    }

}
