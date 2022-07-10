package by.it_academy.jd2.ClassifierService.core.dto.error;

public class SingleError {

    private String logref;
    private String message;

    public SingleError() {
    }

    public SingleError(String logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
