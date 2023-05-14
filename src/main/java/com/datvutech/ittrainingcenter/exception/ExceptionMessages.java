package com.datvutech.ittrainingcenter.exception;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ExceptionMessages {
    private Properties messages;

    public ExceptionMessages() throws IOException {
        messages = new Properties();
        Reader reader = new FileReader(ResourceUtils.getFile("classpath:exception-messages.properties"));
        messages.load(reader);
    }

    public String getMessage(String errorCode) {
        return messages.getProperty(errorCode);
    }
}
