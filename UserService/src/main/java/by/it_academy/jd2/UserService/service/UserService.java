package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.controllers.utils.JwtTokenUtil;
import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.core.entity.User;

import by.it_academy.jd2.UserService.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IUserDao userDao;
    @Autowired
    MapperService mapperService;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userDao.findByMail(mail);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь не найден");
        }
        return user;
    }

    public User create(UserReg dto) {

        User userFromDB = userDao.findByMail(dto.getMail());

        if (userFromDB != null) {
            throw new IllegalArgumentException("пользователь уже существует");
        }

        return this.userDao.save(this.mapperService.mapUserCreate(dto));
    }

    public String login(UserLogin dto) {

        UserDetails details = userDao.findByMail(dto.getMail());

        if(!encoder.matches(dto.getPassword(), details.getPassword())){
            throw new IllegalArgumentException("пароль неверный");
        }
        return JwtTokenUtil.generateAccessToken(details);
    }
}
