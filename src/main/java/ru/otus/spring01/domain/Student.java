package ru.otus.spring01.domain;

/**
 * Created by Stanislav on 03.12.2018
 */
public class Student {

    private final String name;

    private final String lastName;

    private Result result;

    public Student(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public Result getResult() {
        return result;
    }
}
