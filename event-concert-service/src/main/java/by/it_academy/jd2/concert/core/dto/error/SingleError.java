package by.it_academy.jd2.concert.core.dto.error;

public class SingleError {

    private final String logref = "error";
    private String message;

    public SingleError() {
    }

    public SingleError(String message) {
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
