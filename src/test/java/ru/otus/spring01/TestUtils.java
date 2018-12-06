package ru.otus.spring01;

import ru.otus.spring01.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Stanislav on 03.12.2018
 */
public class TestUtils {

    public static void assertEqualsQuestion(Question actual, Question expected) {
        assertEquals(actual.getQuestion(), expected.getQuestion());
        assertEquals(actual.getRightAnswerNumber(), expected.getRightAnswerNumber());
        assertEquals(actual.getAnswersVariant(), expected.getAnswersVariant());
    }

    public static void assertEqualsQuestions(List<Question> actual, List<Question> expected) {
        assertTrue(actual == null && expected == null || actual != null && expected != null);
        if (actual != null) {
            assertEquals(actual.size(), expected.size());
            for (int i = 0; i < actual.size(); i++) {
                assertEqualsQuestion(actual.get(i), expected.get(i));
            }
        }
    }

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "question1",
                Arrays.asList("answer1", "answer2", "answer3"),
                2
        ));
        questions.add(new Question(
                "question2",
                Arrays.asList("answer1", "answer2"),
                1
        ));
        return questions;
    }

}
