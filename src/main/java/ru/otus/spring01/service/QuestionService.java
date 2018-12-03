package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public interface QuestionService {

    List<Question> getQuestions();

}
