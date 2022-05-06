package ru.adm123.springCourse.lesson1.service.resource.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.adm123.springCourse.lesson1.model.Question;
import ru.adm123.springCourse.lesson1.service.resource.ServiceResource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class ServiceResourceCSV implements ServiceResource<Question> {

    private final String fileName;

    public ServiceResourceCSV(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/" + fileName).getFile()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                if (csvLine.length != 2) {
                    throw new RuntimeException("File format error");
                }
                questions.add(new Question(csvLine[0],csvLine[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

}
