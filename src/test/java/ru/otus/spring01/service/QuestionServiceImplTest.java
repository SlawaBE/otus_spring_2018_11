package ru.otus.spring01.service;

import org.testng.annotations.Test;
import ru.otus.spring01.TestUtils;
import ru.otus.spring01.dao.QuestionDao;

import static ru.otus.spring01.TestUtils.assertEqualsQuestions;


/**
 * Created by Stanislav on 03.12.2018
 */
public class QuestionServiceImplTest {

    @Test
    public void testGetQuestions() {
        QuestionDao dao = TestUtils::getQuestions;
        QuestionServiceImpl service = new QuestionServiceImpl(dao);
        assertEqualsQuestions(service.getQuestions(), dao.getQuestions());
    }

}