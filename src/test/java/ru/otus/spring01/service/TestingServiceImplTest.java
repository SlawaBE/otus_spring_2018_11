package ru.otus.spring01.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.spring01.TestUtils;
import ru.otus.spring01.domain.Result;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Stanislav on 04.12.2018
 */
@DisplayName("Класс TestingServiceImpl")
public class TestingServiceImplTest {

    @Test
    @DisplayName("Неудачная инициализация теста")
    public void testFailedInitTest() {
        Locale.setDefault(new Locale("ru_RU"));
        QuestionService questionService = () -> null;
        ByteArrayInputStream is = new ByteArrayInputStream("test\ntest\n".getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new TestingServiceImpl(questionService, null, createMessageSource()).test(is, os);
        String expected = "Введите ваше имя: Введите вашу фамилию: Ошибка! Не удалось загрузить данные для теста\n";
        assertEquals(prepareString(os), expected);
    }

    @Test
    @DisplayName("Прохождение теста")
    public void testSuccessTest() {
        Locale.setDefault(new Locale("ru_RU"));
        QuestionService questionService = TestUtils::getQuestions;
        ResultCheckService resultCheckService = (list) -> new Result(2, 1);
        ByteArrayInputStream is = new ByteArrayInputStream("test\ntest\n1\n0\n".getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new TestingServiceImpl(questionService, resultCheckService, createMessageSource()).test(is, os);
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

    private MessageSource createMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}