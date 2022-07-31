package by.it_academy.jd2.UserService.service.converters;

import by.it_academy.jd2.UserService.core.dto.admin.UserUpdate;
import by.it_academy.jd2.UserService.core.entity.Role;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUpdateToUserConverter implements Converter<UserUpdate, User> {

    private final PasswordEncoder encoder;

    public UserUpdateToUserConverter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Override
    public User convert(UserUpdate dto) {

        User user = new User();

        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setRole(List.of(new Role(dto.getRole())));
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setStatus(dto.getStatus());

        return user;
    }
}
