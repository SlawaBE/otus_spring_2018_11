package ru.otus.spring01.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * Created by Stanislav on 08.12.2018
 */
@PropertySource("application.properties")
@Service
public class ConfigServiceImpl {

    public ConfigServiceImpl(@Value("${locale}") String locale) {
        if (!StringUtils.isEmpty(locale) && !"${locale}".equals(locale)) {
            Locale.setDefault(new Locale(locale));
        }
    }

}
