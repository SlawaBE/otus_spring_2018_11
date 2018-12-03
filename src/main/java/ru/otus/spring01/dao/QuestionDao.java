package ru.otus.spring01.dao;

import ru.otus.spring01.domain.Question;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public interface QuestionDao {

    List<Question> getQuestions();

}
