package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Result;
import ru.otus.spring01.domain.Student;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Stanislav on 03.12.2018
 */
public class TestingServiceImpl implements TestingService {

    private final QuestionService questionService;

    private final ResultCheckService resultCheckService;

    public TestingServiceImpl(QuestionService questionService, ResultCheckService resultCheckService) {
        this.questionService = questionService;
        this.resultCheckService = resultCheckService;
    }

    public void test(InputStream inputStream, OutputStream outputStream) {
        Scanner sc = new Scanner(inputStream);
        PrintWriter pw = new PrintWriter(outputStream);
        Student student = getStudentInfo(sc, pw);
        List<Question> questions = questionService.getQuestions();
        if (questions == null) {
            pw.println("Ошибка! Не удалось загрузить данные для теста");
            return;
        }
        for (Question question : questions) {
            askQuestion(sc, pw, question);
        }
        student.setResult(resultCheckService.checkResult(questions));
        printReport(pw, student);
        pw.close();
    }

    private void printReport(PrintWriter pw, Student student) {
        pw.println();
        pw.println("---------------------------------------");
        pw.println("Результаты теста");
        pw.println("Студент: " + student.getFullName());
        pw.println("---------------------------------------");
        Result result = student.getResult();
        pw.println("Правильных ответов: " + result.getCorrectAnswerAmount());
        pw.println("Всего вопросов:     " + result.getQuestionAmount());
        pw.println("---------------------------------------");
        pw.println("Итоговая оценка:    " + result.getCorrectAnswerAmount() + "/" + result.getQuestionAmount());
        pw.println("---------------------------------------");
    }

    private void askQuestion(Scanner sc, PrintWriter pw, Question question) {
        pw.println(question);
        pw.print("Укажите номер выбранного вами ответа: ");
        pw.flush();
        try {
            int answer = sc.nextInt();
            question.setAnswer(answer);
        } catch (InputMismatchException e) {
            question.setAnswer(-1);
        }
    }

    private Student getStudentInfo(Scanner sc, PrintWriter pw) {
        pw.print("Введите ваше имя: ");
        pw.flush();
        String name = sc.nextLine();
        pw.print("Введите вашу фамилию: ");
        pw.flush();
        String lastName = sc.nextLine();
        return new Student(name, lastName);
    }


}
