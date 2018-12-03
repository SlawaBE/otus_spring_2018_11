package ru.otus.spring01.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Stanislav on 03.12.2018
 */
public interface TestingService {

    void test(InputStream inputStream, OutputStream outputStream);

}
