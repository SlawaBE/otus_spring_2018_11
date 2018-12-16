package ru.otus.spring01.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring01.dao.QuestionDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Stanislav on 03.12.2018
 */
@DisplayName("Класс QuestionServiceImpl")
public class QuestionServiceImplTest {

    @Test
    @DisplayName("Выполняет обращение к questionDao")
    public void testGetQuestions() {
        QuestionDao dao = mock(QuestionDao.class);
        QuestionServiceImpl service = new QuestionServiceImpl(dao);
        service.getQuestions();
        verify(dao).getQuestions();
    }

}