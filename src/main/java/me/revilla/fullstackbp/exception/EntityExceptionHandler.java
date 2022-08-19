package me.revilla.fullstackbp.exception;

import lombok.extern.slf4j.Slf4j;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.exception.entity.EntityErrorMessage;
import me.revilla.fullstackbp.exception.entity.EntityNoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * EntityExceptionHandler
 *
 * @author Revilla Pool
 */
@Slf4j
@RestControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler(value = {EntityNoSuchElementException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public EntityErrorMessage entityNoSuchElement(EntityNoSuchElementException ex,
                                                  WebRequest request) {
        log.error("Error: " + ex.getMessage());
        return new EntityErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                new ApiResponse(Boolean.FALSE, ex.getMessage()),
                request.getDescription(false)
        );
    }

}
