package by.it_academy.jd2.ClassifierService.controllers.advices;

import by.it_academy.jd2.ClassifierService.core.dto.error.SingleError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler (IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SingleError> handle (IllegalArgumentException e){

        List<SingleError> errorResponseList = new ArrayList<>();

        errorResponseList.add(new SingleError(e.getClass().getSimpleName(),e.getMessage()));

        return errorResponseList;
    }


    }
