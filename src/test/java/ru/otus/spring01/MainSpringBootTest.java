package ru.otus.spring01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring01.dao.QuestionDao;
import ru.otus.spring01.dao.QuestionDaoImpl;
import ru.otus.spring01.service.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stanislav on 16.12.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainSpringBootTest {

    @Configuration
    static class Config {
        @Bean
        public MessageSource messageSource() {
            Locale.setDefault(new Locale("ru_RU"));
            ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
            ms.setBasename("/i18n/bundle");
            ms.setDefaultEncoding("UTF-8");
            return ms;
        }

        @Bean
        public QuestionDao questionDao() {
            return new QuestionDaoImpl(messageSource());
        }

        @Bean
        public QuestionService getQuestionService() {
            return new QuestionServiceImpl(questionDao());
        }

        @Bean
        public ResultCheckService resultCheckService() {
            return new ResultCheckServiceImpl();
        }

        @Bean
        public TestingService testingService() {
            return new TestingServiceImpl(getQuestionService(), resultCheckService(), messageSource());
        }
    }

    @Autowired
    private TestingService testingService;

    @Before
    public void init() {
        Locale.setDefault(new Locale("ru_RU"));
    }

    @Test
    public void test() {
        ByteArrayInputStream bais = new ByteArrayInputStream("test\ntest\n2\n2\n".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
        testingService.test(bais, baos);
        assertTrue(baos.size() > 0);
        assertEquals(expected, prepareString(baos));
    }

    private String prepareString(ByteArrayOutputStream os) {
        String str = os.toString();
        return str.replaceAll("\r", "");
    }

}
