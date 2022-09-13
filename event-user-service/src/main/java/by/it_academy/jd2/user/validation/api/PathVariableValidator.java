package by.it_academy.jd2.user.validation.api;

import java.util.UUID;

public interface PathVariableValidator {

    UUID validUUID(String uuid);

    void validUnixTime(Long time);

}
