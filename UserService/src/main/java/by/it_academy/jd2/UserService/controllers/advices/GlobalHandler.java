package by.it_academy.jd2.UserService.controllers.advices;


import by.it_academy.jd2.UserService.core.dto.error.SingleError;
import by.it_academy.jd2.UserService.core.dto.error.FieldError;
import by.it_academy.jd2.UserService.core.dto.error.MultiplyError;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SingleError> handle(IllegalArgumentException e) {

        List<SingleError> errorResponseList = new ArrayList<>();

        errorResponseList.add(new SingleError(e.getMessage()));

        return errorResponseList;
    }

    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SingleError> handle(PSQLException e) {

        List<SingleError> errorResponseList = new ArrayList<>();

        errorResponseList.add(new SingleError(e.getMessage()
                .split("Ключ")[1].trim()
                .replaceAll("\"", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\=", " ")));

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
