package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dao.api.IRoleDao;
import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.entity.Role;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class DefaultUser implements CommandLineRunner {


    private final IRoleDao roleDao;
    private final IUserDao userDao;
    private final PasswordEncoder encoder;

    public DefaultUser(IRoleDao roleDao, IUserDao userDao, PasswordEncoder encoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.encoder = encoder;
    }


    @Override
    public void run(String... args) throws Exception {

        if (roleDao.findByName("ROLE_ADMIN") == null) {

            roleDao.save(new Role("ROLE_ADMIN"));

        }

        if (roleDao.findByName("ROLE_USER") == null) {

            roleDao.save(new Role("ROLE_USER"));

        }

        if (userDao.findByMail("admin@gmail.com") == null) {

            User admin = new User();
            admin.setUuid(UUID.randomUUID());
            admin.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
            admin.setDtUpdate(admin.getDtCreate());
            admin.setMail("admin@gmail.com");
            admin.setNick("admin");
            admin.setRole(List.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
            admin.setStatus(UserStatus.ACTIVATED);
            admin.setPassword(encoder.encode("admin"));

            userDao.save(admin);
        }

        if (userDao.findByMail("user@gmail.com") == null) {

            User user = new User();
            user.setUuid(UUID.randomUUID());
            user.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
            user.setDtUpdate(user.getDtCreate());
            user.setMail("user@gmail.com");
            user.setNick("user");
            user.setRole(List.of(new Role("ROLE_USER")));
            user.setStatus(UserStatus.ACTIVATED);
            user.setPassword(encoder.encode("user"));

            userDao.save(user);
        }
    }
}
