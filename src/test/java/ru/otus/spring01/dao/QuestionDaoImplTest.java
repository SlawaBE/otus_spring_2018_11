package ru.otus.spring01.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import ru.otus.spring01.TestUtils;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.otus.spring01.TestUtils.assertEqualsQuestions;


/**
 * Created by Stanislav on 03.12.2018
 */
@DisplayName("Класс QuestionDaoImpl")
public class QuestionDaoImplTest {

    @Test
    @DisplayName("Получаем null при неудачном чтении файла")
    public void testFailGetQuestions() {
        MessageSource mock = mock(MessageSource.class);
        when(mock.getMessage(anyString(), any(), any(Locale.class))).thenReturn("not_found.csv");
        QuestionDaoImpl dao = new QuestionDaoImpl(mock);
        assertNull(dao.getQuestions());
    }

    @Test()
    @DisplayName("Успешное получение списка вопросов")
    public void testGetQuestions() {
        MessageSource mock = mock(MessageSource.class);
        when(mock.getMessage(anyString(), any(), any(Locale.class))).thenReturn("test.csv");
        QuestionDaoImpl dao = new QuestionDaoImpl(mock);
        assertEqualsQuestions(dao.getQuestions(), TestUtils.getQuestions());
    }
}