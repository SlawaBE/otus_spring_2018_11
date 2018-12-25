package ru.otus.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Result;
import ru.otus.spring01.domain.Student;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Stanislav on 03.12.2018
 */
@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionService questionService;

    private final ResultCheckService resultCheckService;

    private final MessageSource messageSource;

    @Autowired
    public TestingServiceImpl(QuestionService questionService, ResultCheckService resultCheckService, MessageSource messageSource) {
        this.questionService = questionService;
        this.resultCheckService = resultCheckService;
        this.messageSource = messageSource;
    }

    public void test(InputStream inputStream, OutputStream outputStream) {
        Scanner sc = new Scanner(inputStream);
        PrintWriter pw = new PrintWriter(outputStream);
        Student student = getStudentInfo(sc, pw);
        List<Question> questions = questionService.getQuestions();
        if (questions == null) {
            pw.println(messageSource.getMessage("test.error.download", new String[0], Locale.getDefault()));
            pw.close();
            return;
        }
        for (Question question : questions) {
            askQuestion(sc, pw, question);
        }
        student.setResult(resultCheckService.checkResult(questions));
        printReport(pw, student);
        pw.flush();
    }

    private void printReport(PrintWriter pw, Student student) {
        pw.println();
        pw.println("---------------------------------------");
        pw.println(messageSource.getMessage("test.result", new String[0], Locale.getDefault()));
        pw.println(messageSource.getMessage("test.student", new String[]{student.getFullName()}, Locale.getDefault()));
        pw.println("---------------------------------------");
        Result result = student.getResult();
        pw.println(messageSource.getMessage("test.correct.answers", new Integer[]{result.getCorrectAnswerAmount()}, Locale.getDefault()));
        pw.println(messageSource.getMessage("test.answers.count", new Integer[]{result.getQuestionAmount()}, Locale.getDefault()));
        pw.println("---------------------------------------");
        pw.println(messageSource.getMessage("test.final.grade", new Integer[]{result.getCorrectAnswerAmount(), result.getQuestionAmount()}, Locale.getDefault()));
        pw.println("---------------------------------------");
    }

    private void askQuestion(Scanner sc, PrintWriter pw, Question question) {
        pw.println(question);
        pw.print(messageSource.getMessage("test.enter.number", new String[0], Locale.getDefault()));
        pw.flush();
        try {
            int answer = sc.nextInt();
            question.setAnswer(answer);
        } catch (InputMismatchException e) {
            question.setAnswer(-1);
        }
    }

    private Student getStudentInfo(Scanner sc, PrintWriter pw) {
        pw.print(messageSource.getMessage("test.enter.name", new String[0], Locale.getDefault()));
        pw.flush();
        String name = sc.nextLine();
        pw.print(messageSource.getMessage("test.enter.lastName", new String[0], Locale.getDefault()));
        pw.flush();
        String lastName = sc.nextLine();
        return new Student(name, lastName);
    }


}
