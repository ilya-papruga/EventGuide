package by.it_academy.jd2.user.service.converters;

import by.it_academy.jd2.user.core.dto.admin.UserCreate;
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
public class UserCreateToUserConverter implements Converter <UserCreate, User> {

    private final PasswordEncoder encoder;

    public UserCreateToUserConverter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User convert(UserCreate dto) {

        User entity = new User();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(dto.getMail());
        entity.setNick(dto.getNick());
        entity.setRole(List.of(new Role(dto.getRole())));
        entity.setStatus(UserStatus.ACTIVATED);
        entity.setPassword(encoder.encode(dto.getPassword()));

        return entity;
    }
}
