package com.datvutech.ittrainingcenter.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class AppProperties {
    private Properties configs;

    public AppProperties() throws IOException {
        configs = new Properties();
        Reader reader = new FileReader(ResourceUtils.getFile("classpath:configs.properties"));
        configs.load(reader);
    }

    public String getMessage(String errorCode) {
        return configs.getProperty(errorCode);
    }
}
