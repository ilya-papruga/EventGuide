package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.entity.Role;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DefaultUser implements CommandLineRunner {


    private final IUserDao dao;
    private final PasswordEncoder encoder;

    public DefaultUser(IUserDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }


    @Override
    public void run(String... args) throws Exception {

        if (dao.findByMail("admin@gmail.com") == null) {

            User admin = new User();
            admin.setUuid(UUID.randomUUID());
            admin.setDtCreate(LocalDateTime.now());
            admin.setDtUpdate(admin.getDtCreate());
            admin.setMail("admin@gmail.com");
            admin.setNick("admin");
            admin.setRole(List.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
            admin.setStatus(UserStatus.ACTIVATED);
            admin.setPassword(encoder.encode("admin"));

            dao.save(admin);
        }
    }
}
