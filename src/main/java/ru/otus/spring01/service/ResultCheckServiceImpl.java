package ru.otus.spring01.service;

import org.springframework.stereotype.Service;
import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Result;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
@Service
public class ResultCheckServiceImpl implements ResultCheckService {

    @Override
    public Result checkResult(List<Question> questions) {
        int questionAmount = 0;
        int correctAnswerAmount = 0;

        for (Question question : questions) {
            if (question.isCorrectAnswer()) {
                correctAnswerAmount++;
            }
            questionAmount++;
        }

        return new Result(questionAmount, correctAnswerAmount);
    }
}
