package ru.otus.spring01;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring01.domain.Result;
import ru.otus.spring01.service.QuestionService;
import ru.otus.spring01.service.ResultCheckService;
import ru.otus.spring01.service.TestingService;
import ru.otus.spring01.service.TestingServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

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

        @MockBean
        public static QuestionService questionService;

        @MockBean
        public static ResultCheckService resultCheckService;

        @Bean
        public TestingService getTestingService() {
            return new TestingServiceImpl(questionService, resultCheckService, messageSource());
        }
    }

    @Autowired
    private TestingService testingService;

    @Before
    public void init() {
        when(Config.resultCheckService.checkResult(any(List.class))).thenReturn(new Result(1, 1));
    }

    @Test
    public void test() {
        ByteArrayInputStream bais = new ByteArrayInputStream("test\ntest\n2\n1\n2\n3\n2\n".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        testingService.test(bais, baos);
        verify(Config.questionService).getQuestions();
        verify(Config.resultCheckService).checkResult(anyList());
        assertTrue(baos.size() > 0);
        System.out.println(baos.toString());
    }

}
