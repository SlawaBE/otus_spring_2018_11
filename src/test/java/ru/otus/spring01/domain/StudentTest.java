package ru.otus.spring01.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Stanislav on 03.12.2018
 */
@DisplayName("Класс Student")
public class StudentTest {

    @Test
    @DisplayName("Проверка корректности создания")
    public void testConstruction() {
        String name = "name";
        String lastName = "lastName";
        Student student = new Student("name", "lastName");
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("lastName", lastName)
                .hasFieldOrPropertyWithValue("result", null);
    }

    @Test
    @DisplayName("Проверка установки результата")
    public void testSetResult() {
        Student student = new Student(null, null);
        Result result = new Result(0, 0);
        student.setResult(result);
        assertThat(student).hasFieldOrPropertyWithValue("result", result);
    }

    @Test
    @DisplayName("Проверка получения полного имени")
    public void testGetFullName() {
        String expected = "name lastName";
        Student student = new Student("name", "lastName");
        assertEquals(student.getFullName(), expected);
    }

    @Test
    @DisplayName("Проверка получения результата")
    public void testGetResult() {
        Student student = new Student(null, null);
        Result result = new Result(0, 0);
        student.setResult(result);
        assertEquals(student.getResult(), result);
    }
}