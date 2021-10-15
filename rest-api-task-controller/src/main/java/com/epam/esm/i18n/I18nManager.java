package com.epam.esm.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Ivan Velichko
 * @date 15.10.2021 11:18
 */

@Component
public class I18nManager {
    private final ResourceBundleMessageSource resourceBundleMessageSource;
    private static final List<String> AVAILABLE_LOCALES = Arrays.asList("en_US", "ru_RU");
    private static final Locale DEFAULT_LOCALE = new Locale("en", "US");

    @Autowired
    public I18nManager(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public List<String> getLocaleValidationErrorMessages(BindingResult bindingResult, Locale locale) {
        List<String> errors = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError error : errorList) {
                String localeMsg = getMessage(error.getDefaultMessage(), locale);
                errors.add(localeMsg);
            }
        }
        return errors;
    }

    public String getMessage(String key, Locale locale) {
        if (!AVAILABLE_LOCALES.contains(locale.toString())) {
            locale = DEFAULT_LOCALE;
        }
        return resourceBundleMessageSource.getMessage(key, null, locale);
    }
}
