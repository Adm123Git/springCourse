package ru.adm123.springCourse.lesson1.service.resource.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.adm123.springCourse.lesson1.model.Question;
import ru.adm123.springCourse.lesson1.service.resource.ServiceResource;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static ru.adm123.springCourse.lesson1.util.UtilString.hasText;

/**
 * Реализация интерфейса {@link ServiceResource}, обеспечивающая работу с объектами {@link Question}, заданными в CSV-файле
 */
public class ServiceResourceQuestionCSV implements ServiceResource<Question> {

    private final String fileName;

    public ServiceResourceQuestionCSV(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getAll() {
        if (!hasText(fileName)) {
            throw new RuntimeException("File not found");
        }
        List<Question> questions = new ArrayList<>();
        URL fileURL = getClass().getResource("/" + fileName);
        if (fileURL == null) {
            throw new RuntimeException("File not found");
        }
        try (CSVReader csvReader = new CSVReader(new FileReader(fileURL.getFile()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                if (csvLine.length != 2) {
                    throw new RuntimeException("File format error");
                }
                questions.add(new Question(csvLine[0], csvLine[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

}
