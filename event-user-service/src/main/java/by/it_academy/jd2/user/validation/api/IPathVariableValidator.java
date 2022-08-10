package by.it_academy.jd2.user.validation.api;

import java.util.UUID;

public interface IPathVariableValidator {

    UUID validUUID(String uuid);

    void validUnixTime(Long time);

}
