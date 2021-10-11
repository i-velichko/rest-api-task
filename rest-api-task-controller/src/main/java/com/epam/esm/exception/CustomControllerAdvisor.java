package com.epam.esm.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.epam.esm.exception.CustomErrorMessageCode.ENTITY_NOT_FOUND;
import static com.epam.esm.exception.CustomErrorMessageCode.TAG_CAN_NOT_BE_REMOVED;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 10:37
 */

@ControllerAdvice
public class CustomControllerAdvisor {
    private final ResourceBundleMessageSource resourceBundleMessageSource;
    private static final List<String> AVAILABLE_LOCALES = Arrays.asList("en_US", "ru_RU");
    private static final Locale DEFAULT_LOCALE = new Locale("en", "US");

    @Autowired
    public CustomControllerAdvisor(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<CustomResponse> handleNoSuchEntityException(NoSuchEntityException e, Locale locale) {
        String localeMsg = resolveResourceBundle(e.getMessage(), locale);
        CustomResponse response = new CustomResponse(localeMsg, 40401);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<CustomResponse> handleDuplicateEntityException(DuplicateEntityException e, Locale locale) {
        String localeMsg = resolveResourceBundle(e.getMessage(), locale);
        CustomResponse response = new CustomResponse(localeMsg, 40404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<CustomResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException e, Locale locale) {
        String msg = getEntityNameByMsg(e, ".", "Dao").toLowerCase(Locale.ROOT);
        String localeMsg = resolveResourceBundle(ENTITY_NOT_FOUND + msg, locale);
        CustomResponse response = new CustomResponse(localeMsg, 40402);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e, Locale locale) {
        String localeMsg = resolveResourceBundle(TAG_CAN_NOT_BE_REMOVED, locale);
        CustomResponse response = new CustomResponse(localeMsg, 40403);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    private String getEntityNameByMsg(Exception e, String beforeName, String afterName) {
        String text = msgFromStack(e, afterName);
        return text.substring(text.lastIndexOf(beforeName) + 1, text.indexOf(afterName));
    }

    private String msgFromStack(Exception e, String afterName) {
        Optional<String> dao = Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::getClassName)
                .filter(className -> className.contains(afterName))
                .findAny();
        return dao.get();
    }

    private String resolveResourceBundle(String key, Locale locale) {
        if (!AVAILABLE_LOCALES.contains(locale.toString())) {
            locale = DEFAULT_LOCALE;
        }
        return resourceBundleMessageSource.getMessage(key, null, locale);
    }

}
