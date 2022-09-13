package by.it_academy.jd2.film.validation.api;

import java.util.UUID;

public interface PathVariableValidator {

    UUID validUUID(String uuid);

    void validUnixTime(Long time);

}
