package ru.otus.spring01.service;

import org.testng.annotations.Test;
import ru.otus.spring01.TestUtils;
import ru.otus.spring01.domain.Result;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

import static org.testng.Assert.*;


/**
 * Created by Stanislav on 04.12.2018
 */
public class TestingServiceImplTest {

    @Test
    public void testFailedInitTest() {
        Locale.setDefault(Locale.US);
        QuestionService questionService = () -> null;
        ByteArrayInputStream is = new ByteArrayInputStream("test\ntest\n".getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new TestingServiceImpl(questionService, null).test(is, os);
        String expected = "Введите ваше имя: Введите вашу фамилию: ";
        assertEquals(prepareString(os), expected);
    }

    @Test
    public void testSuccessTest() {
        Locale.setDefault(Locale.US);
        QuestionService questionService = TestUtils::getQuestions;
        ResultCheckService resultCheckService = (list) -> new Result(2,1);
        ByteArrayInputStream is = new ByteArrayInputStream("test\ntest\n1\n0\n".getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new TestingServiceImpl(questionService, resultCheckService).test(is, os);
        String expected = "Введите ваше имя: Введите вашу фамилию: question1\n" +
                "0) answer1\n" +
                "1) answer2\n" +
                "2) answer3\n" +
                "\n" +
                "Укажите номер выбранного вами ответа: question2\n" +
                "0) answer1\n" +
                "1) answer2\n" +
                "\n" +
                "Укажите номер выбранного вами ответа: \n" +
                "---------------------------------------\n" +
                "Результаты теста\n" +
                "Студент: test test\n" +
                "---------------------------------------\n" +
                "Правильных ответов: 1\n" +
                "Всего вопросов:     2\n" +
                "---------------------------------------\n" +
                "Итоговая оценка:    1/2\n" +
                "---------------------------------------\n";
        assertEquals(prepareString(os), expected);
    }

    private String prepareString(ByteArrayOutputStream os) {
        String str = os.toString();
        return str.replaceAll("\r", "");
    }

}