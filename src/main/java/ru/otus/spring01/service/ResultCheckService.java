package ru.otus.spring01.service;

import ru.otus.spring01.domain.Question;
import ru.otus.spring01.domain.Result;

import java.util.List;

/**
 * Created by Stanislav on 03.12.2018
 */
public interface ResultCheckService {

    Result checkResult(List<Question> questions);

}
