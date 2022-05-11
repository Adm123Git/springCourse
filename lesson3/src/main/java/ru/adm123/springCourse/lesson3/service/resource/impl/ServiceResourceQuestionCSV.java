package ru.adm123.springCourse.lesson3.service.resource.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson3.aspect.anno.LogExecuteTime;
import ru.adm123.springCourse.lesson3.model.Question;
import ru.adm123.springCourse.lesson3.service.application.ServiceAppProperties;
import ru.adm123.springCourse.lesson3.service.resource.ServiceResource;
import ru.adm123.springCourse.lesson3.util.UtilString;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link ServiceResource}, обеспечивающая работу с объектами {@link Question}, заданными в CSV-файле
 */
@Service
public class ServiceResourceQuestionCSV implements ServiceResource<Question> {

    private final ServiceAppProperties serviceAppProperties;

    @Autowired
    public ServiceResourceQuestionCSV(ServiceAppProperties serviceAppProperties) {
        this.serviceAppProperties = serviceAppProperties;
    }

    @Override
    @LogExecuteTime
    public List<Question> getAll() {
        String fileName = serviceAppProperties.getQuestionCsvFilePath();
        if (!UtilString.hasText(fileName)) {
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
