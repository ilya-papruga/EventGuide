package by.it_academy.jd2.UserService.service.converters;

import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserReadConverter implements Converter<User, UserRead> {

    @Override
    public UserRead convert(User entity) {

        UserRead dto = new UserRead();

        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setMail(entity.getMail());
        dto.setNick(entity.getNick());
        dto.setRoles(entity.getStringRoles());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
