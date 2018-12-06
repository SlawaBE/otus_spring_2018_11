package ru.otus.spring01.domain;

/**
 * Created by Stanislav on 03.12.2018
 */
public class Result {

    private final int questionAmount;

    private final int correctAnswerAmount;

    public Result(int questionAmount, int correctAnswerAmount) {
        this.questionAmount = questionAmount;
        this.correctAnswerAmount = correctAnswerAmount;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public int getCorrectAnswerAmount() {
        return correctAnswerAmount;
    }
}
