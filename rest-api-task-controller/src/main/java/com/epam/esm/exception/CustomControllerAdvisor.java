package com.epam.esm.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Ivan Velichko
 * @date 04.10.2021 10:37
 */

@ControllerAdvice
public class CustomControllerAdvisor {

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<CustomResponse> handleNoSuchEntityException(NoSuchEntityException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        CustomResponse response = new CustomResponse(message, 40401);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<CustomResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        CustomResponse response = new CustomResponse(
                getEntityNameByMsg(e, ".", "Dao") + " is not found.", 40401);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    private String getEntityNameByMsg(Exception e, String beforeName, String afterName) {
        String text = msgFromStack(e);
        int temp = text.indexOf(beforeName);
        int index;
        while (true) {
            index = text.indexOf(beforeName, temp + 1);
            if (index == -1) {
                break;
            }
            temp = index;
        }
        int dao = text.indexOf(afterName, temp);
        return text.substring(temp + 1, dao);
    }

    private String msgFromStack(Exception e) {
        Optional<String> dao = Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::getClassName)
                .filter(className -> className.contains("Dao"))
                .findAny();
        return dao.get();
    }

}
