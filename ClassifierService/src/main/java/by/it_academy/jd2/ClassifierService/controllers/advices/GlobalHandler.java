package by.it_academy.jd2.ClassifierService.controllers.advices;

import by.it_academy.jd2.ClassifierService.core.dto.error.FieldError;
import by.it_academy.jd2.ClassifierService.core.dto.error.MultiplyError;
import by.it_academy.jd2.ClassifierService.core.dto.error.SingleError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler (IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SingleError> handle (IllegalArgumentException e){

        List<SingleError> errorResponseList = new ArrayList<>();

        errorResponseList.add(new SingleError(e.getMessage()));

        return errorResponseList;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MultiplyError handle(ConstraintViolationException e) {
        List<FieldError> fieldError = new ArrayList<>();

        String field = null;

        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {

            for (Path.Node node : constraintViolation.getPropertyPath()) {
                field = node.getName();
            }
            fieldError.add(new FieldError(constraintViolation.getMessage(), field));
        }
        return new MultiplyError(fieldError);
    }


    }
