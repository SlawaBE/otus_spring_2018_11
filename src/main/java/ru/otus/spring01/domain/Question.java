package ru.otus.spring01.domain;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public class Question {

    private final String question;

    private final List<String> answersVariant;

    private final int rightAnswerNumber;

    private int answer;

    public Question(String question, List<String> answersVariant, int rightAnswerNumber) {
        this.question = question;
        this.answersVariant = answersVariant;
        this.rightAnswerNumber = rightAnswerNumber;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswersVariant() {
        return answersVariant;
    }

    public int getRightAnswerNumber() {
        return rightAnswerNumber;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isCorrectAnswer() {
        return answer == rightAnswerNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(question);
        sb.append("\n");
        for (int i = 0; i < answersVariant.size(); i++) {
            sb.append(i).append(") ").append(answersVariant.get(i)).append("\n");
        }
        return sb.toString();
    }
}
