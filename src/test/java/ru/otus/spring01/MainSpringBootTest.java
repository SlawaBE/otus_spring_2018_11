package ru.otus.spring01;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring01.dao.QuestionDao;
import ru.otus.spring01.service.QuestionService;
import ru.otus.spring01.service.TestingService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stanislav on 16.12.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainSpringBootTest {

    @Autowired
    private TestingService testingService;

    @Autowired
    private QuestionDao dao;

    @Autowired
    private QuestionService questionService;

    @Test
    public void test() {
        assertEquals(dao.getQuestions().size(), 5);
        assertEquals(questionService.getQuestions().size(), 5);
        ByteArrayInputStream bais = new ByteArrayInputStream("test\ntest\n2\n1\n2\n3\n2\n".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        testingService.test(bais, baos);
        assertTrue(baos.size() > 0);
    }

}
