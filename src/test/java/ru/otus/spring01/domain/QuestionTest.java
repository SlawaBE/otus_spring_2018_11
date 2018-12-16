package ru.otus.spring01.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Stanislav on 03.12.2018
 */
@DisplayName("Класс Question")
public class QuestionTest {

    @Test
    @DisplayName("Корректно создаётся")
    public void testConstruction() {
        String questionText = "question";
        List<String> answers = Arrays.asList("answer1", "answer2");
        int correctAnswer = 1;
        Question question = new Question(questionText, answers, correctAnswer);
        assertThat(question)
                .hasFieldOrPropertyWithValue("question", questionText)
                .hasFieldOrPropertyWithValue("rightAnswerNumber", correctAnswer)
                .hasFieldOrPropertyWithValue("answersVariant", answers);
    }

    @Test
    @DisplayName("Проверка правильного ответа")
    public void testIsCorrectAnswer() {
        int correct = 100;
        Question question = new Question(null, null, correct);
        question.setAnswer(correct);
        assertTrue(question.isCorrectAnswer());
    }

    @Test
    @DisplayName("Проверка неправильного ответа")
    public void testIsIncorrectAnswer() {
        int correct = 123;
        int incorrect = 100;
        Question question = new Question(null, null, correct);
        question.setAnswer(incorrect);
        assertFalse(question.isCorrectAnswer());
    }

    @Test
    @DisplayName("Проверка преобразования в строку")
    public void testToString() {
        String expected = "question\n0) answer1\n1) answer2\n";
        Question question = new Question("question", Arrays.asList("answer1", "answer2"), 3);
        String actual = question.toString();
        assertEquals(actual, expected);
    }
}