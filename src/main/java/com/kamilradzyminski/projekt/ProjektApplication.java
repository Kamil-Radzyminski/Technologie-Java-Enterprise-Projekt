package com.kamilradzyminski.projekt;

import com.kamilradzyminski.projekt.utitles.CsvToXmlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication

public class ProjektApplication {
    public static void main(String[] args) {
        CsvToXmlParser.parserXml();
        SpringApplication.run(ProjektApplication.class, args);
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
}
