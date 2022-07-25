package com.example.WebApplication.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ModifiedExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    public static ResponseEntity<Object> handleEmailConflictException(HttpClientErrorException.Conflict ex,
                                                                     WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Email exists", details);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

}
