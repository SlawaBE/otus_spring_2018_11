package ru.otus.spring01.dao;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import ru.otus.spring01.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public class QuestionDaoImpl implements QuestionDao {

    private final String fileName;

    public QuestionDaoImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getQuestions() {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                new ClassPathResource(fileName).getInputStream()
        ))) {
            List<String[]> items = csvReader.readAll();
            List<Question> questions = new ArrayList<>();
            for (String[] str : items) {
                questions.add(new Question(
                        str[0],
                        Arrays.asList(str[1].split(";")),
                        Integer.parseInt(str[2]))
                );
            }
            return questions;
        } catch (IOException e) {
            return null;
        }
    }
}
