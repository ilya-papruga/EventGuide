package by.it_academy.jd2.UserService.core.dto.error;

import java.util.List;

public class MultiplyError {

    private final String logref = "structured_error";

    private List<FieldError> errors;

    public MultiplyError() {
    }

    public MultiplyError(List<FieldError> errors) {
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
