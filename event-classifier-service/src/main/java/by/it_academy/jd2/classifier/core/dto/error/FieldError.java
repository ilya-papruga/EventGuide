package by.it_academy.jd2.classifier.core.dto.error;

public class FieldError {

    private String message;
    private String filed;

    public FieldError() {
    }

    public FieldError(String message, String filed) {
        this.message = message;
        this.filed = filed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }
}
