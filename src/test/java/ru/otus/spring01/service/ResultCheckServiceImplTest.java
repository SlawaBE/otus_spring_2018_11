package ru.otus.spring01.service;

import org.testng.annotations.Test;
import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Result;

import java.util.List;

import static org.testng.Assert.*;
import static ru.otus.spring01.TestUtils.getQuestions;


/**
 * Created by Stanislav on 04.12.2018
 */
public class ResultCheckServiceImplTest {

    @Test
    public void testCheckResult() {
        List<Question> questions = getQuestions();
        questions.get(0).setAnswer(2);
        questions.get(1).setAnswer(0);

        ResultCheckServiceImpl resultCheckService = new ResultCheckServiceImpl();
        Result result = resultCheckService.checkResult(questions);

        assertEquals(result.getQuestionAmount(), questions.size());
        assertEquals(result.getCorrectAnswerAmount(), 1);
    }
}