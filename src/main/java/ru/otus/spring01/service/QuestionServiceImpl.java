package ru.otus.spring01.service;

import ru.otus.spring01.dao.QuestionDao;
import ru.otus.spring01.domain.Question;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() {
        return dao.getQuestions();
    }
}
