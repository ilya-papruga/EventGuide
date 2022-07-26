package by.it_academy.jd2.UserService.validation;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class PathVariableValidator {

    public UUID isValidUUID(String uuid) {
        Pattern regex = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
        if (!regex.matcher(uuid).matches()){
            throw new IllegalArgumentException ("невалидный uuid");
        }
        return UUID.fromString(uuid);
    }

    public void isValidUnixTime(Long time){

        if (time.toString().length() != 13) {
            throw new IllegalArgumentException("невалидная дата");
        }

    }
}
