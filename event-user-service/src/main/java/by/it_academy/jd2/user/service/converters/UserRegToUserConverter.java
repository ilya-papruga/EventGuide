package by.it_academy.jd2.user.service.converters;

import by.it_academy.jd2.user.core.dto.user.UserReg;
import by.it_academy.jd2.user.core.entity.Role;
import by.it_academy.jd2.user.core.entity.User;
import by.it_academy.jd2.user.core.entity.enums.UserStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Component
public class UserRegToUserConverter implements Converter <UserReg, User> {

    private final PasswordEncoder encoder;

    public UserRegToUserConverter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User convert(UserReg dto) {

        User entity = new User();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(dto.getMail());
        entity.setNick(dto.getNick());
        entity.setRole(List.of(new Role("ROLE_USER")));
        entity.setStatus(UserStatus.WAITING_ACTIVATION);
        entity.setPassword(encoder.encode(dto.getPassword()));

        return entity;

    }
}
