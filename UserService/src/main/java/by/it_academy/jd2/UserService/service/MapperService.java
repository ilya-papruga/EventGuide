package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.dto.page.PageRead;
import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.core.entity.Role;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MapperService implements IMapperService {

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserRead mapRead(User user) {

        UserRead dto = new UserRead();

        dto.setUuid(user.getUuid());
        dto.setDtCreate(user.getDtCreate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setMail(user.getMail());
        dto.setNick(user.getNick());
        dto.setRoles(user.getStringRoles());
        dto.setStatus(user.getStatus());

        return dto;
    }

    @Override
    public User mapAdminCreate(UserCreateUpdate dto) {

        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setRole(List.of(new Role("ROLE_" + dto.getRole())));
        user.setStatus(dto.getStatus());
        user.setPassword(encoder.encode(dto.getPassword()));

        return user;
    }

    @Override
    public User mapUserCreate(UserReg dto) {

        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setRole(List.of(new Role("ROLE_USER")));
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setPassword(encoder.encode(dto.getPassword()));

        return user;
    }

    @Override
    public User mapUpdate(UserCreateUpdate dto, User userDB) {

        User user = new User();

        user.setUuid(userDB.getUuid());
        user.setDtCreate(userDB.getDtCreate());
        user.setDtUpdate(userDB.getDtUpdate());
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setRole(List.of(new Role("ROLE_" +dto.getRole())));
        user.setStatus(dto.getStatus());
        user.setPassword(encoder.encode(dto.getPassword()));

        return user;
    }

    @Override
    public PageRead<UserRead> mapPage(Page<User> page) {

        PageRead<UserRead> dtoPage = new PageRead<>();

        List<UserRead> dtoContent = new ArrayList<>();

        for (User user : page.getContent()) {
            dtoContent.add(mapRead(user));
        }
        dtoPage.setNumber(page.getNumber());
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;

    }
}