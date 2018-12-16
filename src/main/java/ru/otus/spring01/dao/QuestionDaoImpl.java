package ru.otus.spring01.dao;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.otus.spring01.domain.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Stanislav on 03.12.2018
 */
@Service
public class QuestionDaoImpl implements QuestionDao {

    private final MessageSource messageSource;

    @Autowired
    public QuestionDaoImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public List<Question> getQuestions() {
        String fileName = messageSource.getMessage("question.file.name", new String[0], Locale.getDefault());
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                new ClassPathResource(fileName).getInputStream()
        ))) {
            List<String[]> items = csvReader.readAll();
            List<Question> questions = new ArrayList<>();
            for (String[] str : items) {
                if (str.length == 3) {
                    questions.add(new Question(
                            str[0],
                            Arrays.asList(str[1].split(";")),
                            Integer.parseInt(str[2]))
                    );
                }
            }
            return questions;
        } catch (IOException e) {
            return null;
        }
    }
}
