package ru.otus.spring01.dao;

import org.testng.annotations.Test;
import ru.otus.spring01.TestUtils;

import static org.testng.Assert.assertNull;
import static ru.otus.spring01.TestUtils.assertEqualsQuestions;


/**
 * Created by Stanislav on 03.12.2018
 */
public class QuestionDaoImplTest {

    @Test
    public void testFailGetQuestions() {
        QuestionDaoImpl dao = new QuestionDaoImpl("not_found.csv");
        assertNull(dao.getQuestions());
    }

    @Test()
    public void testGetQuestions() {
        QuestionDaoImpl dao = new QuestionDaoImpl("test.csv");
        assertEqualsQuestions(dao.getQuestions(), TestUtils.getQuestions());
    }
}