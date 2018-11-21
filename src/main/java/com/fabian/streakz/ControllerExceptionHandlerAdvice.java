package com.fabian.streakz;

import com.fabian.streakz.exception.ActivityAlreadyExistsException;
import com.fabian.streakz.exception.ActivityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ActivityNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Activity not found.")
    public void ActivityNotFoundException(ActivityNotFoundException exception) {
        logError(exception);
    }

    @ExceptionHandler({ActivityAlreadyExistsException.class})
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Activity already exists.")
    public void ActivityAlreadyExistsException(ActivityAlreadyExistsException exception) {
        logError(exception);
    }


    private void logError(Exception exception) {
        log.error("Error in controller:", exception);
    }
}
