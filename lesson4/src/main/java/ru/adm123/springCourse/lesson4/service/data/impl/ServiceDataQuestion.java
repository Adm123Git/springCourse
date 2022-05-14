package ru.adm123.springCourse.lesson4.service.data.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adm123.springCourse.lesson4.aspect.anno.LogExecuteTime;
import ru.adm123.springCourse.lesson4.model.Question;
import ru.adm123.springCourse.lesson4.model.TestParam;
import ru.adm123.springCourse.lesson4.service.data.ServiceData;
import ru.adm123.springCourse.lesson4.service.resource.ServiceBundleResource;
import ru.adm123.springCourse.lesson4.util.UtilString;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link ServiceData}, обеспечивающая работу с данными типа {@link Question}
 */
@Service
public class ServiceDataQuestion implements ServiceData<Question> {

    private final ServiceBundleResource serviceBundleResourceQuestionImpl;
    private final TestParam testParam;

    @Autowired
    public ServiceDataQuestion(ServiceBundleResource serviceBundleResourceQuestionImpl,
                               TestParam testParam) {
        this.serviceBundleResourceQuestionImpl = serviceBundleResourceQuestionImpl;
        this.testParam = testParam;
    }

    @Override
    @LogExecuteTime
    public List<Question> getAll() {
        String fileName = testParam.getQuestionList();
        if (!UtilString.hasText(fileName)) {
            throw new RuntimeException("fileNotFound");
        }
        List<Question> questions = new ArrayList<>();
        URL fileURL = getClass().getResource("/" + fileName);
        if (fileURL == null) {
            throw new RuntimeException("fileNotFound");
        }
        try (CSVReader csvReader = new CSVReader(new FileReader(fileURL.getFile()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                if (csvLine.length != 2) {
                    throw new RuntimeException("fileFormatError");
                }
                questions.add(new Question(serviceBundleResourceQuestionImpl.getString(csvLine[0]), csvLine[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

}
