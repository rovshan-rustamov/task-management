package com.example.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Slf4j
@Service
@RequiredArgsConstructor
public class TranslationService {

    private final MessageSource messageSource;



    public String findByKey(String key, String lang, Object... arguments) {
        try {
            return messageSource.getMessage(key, arguments, new Locale(lang, lang));
        } catch (NoSuchMessageException ex) {
            return key;
        }
    }

}

