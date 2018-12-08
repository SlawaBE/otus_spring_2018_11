package ru.otus.spring01.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Stanislav on 03.12.2018
 */
@DisplayName("Класс Result")
public class ResultTest {

    @Test
    @DisplayName("Проверка корректности создания")
    public void testConstruction() {
        int questionAmount = 5;
        int correctAnswerAmount = 3;
        Result result = new Result(questionAmount, correctAnswerAmount);
        assertThat(result)
                .hasFieldOrPropertyWithValue("questionAmount", questionAmount)
                .hasFieldOrPropertyWithValue("correctAnswerAmount", correctAnswerAmount);
    }

    @Test
    @DisplayName("Проверка получения общего числа ответов")
    public void testGetQuestionAmount() {
        int test = 123;
        int expected = 123;
        Result result = new Result(test, 0);
        assertEquals(result.getQuestionAmount(), expected);
    }

    @Test
    @DisplayName("Првоерка получения числа правильных ответов")
    public void testGetCorrectAnswerAmount() {
        int test = 123;
        int expected = 123;
        Result result = new Result(0, test);
        assertEquals(result.getCorrectAnswerAmount(), expected);
    }
}