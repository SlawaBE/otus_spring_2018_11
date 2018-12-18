package ru.otus.spring01.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

/**
 * Created by Stanislav on 08.12.2018
 */
@ConfigurationProperties
public class ConfigServiceImpl {

    public void setLocale(String value) {
        Locale.setDefault(new Locale(value));
    }

}
