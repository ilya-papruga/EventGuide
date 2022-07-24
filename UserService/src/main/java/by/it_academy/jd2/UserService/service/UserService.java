package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.controllers.utils.JwtTokenUtil;
import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.core.entity.User;

import by.it_academy.jd2.UserService.service.api.IMapperService;
import by.it_academy.jd2.UserService.service.api.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {


    private final PasswordEncoder encoder;
    private final IUserDao userDao;
    private final IMapperService mapperService;

    public UserService(PasswordEncoder encoder, IUserDao userDao, IMapperService mapperService) {
        this.encoder = encoder;
        this.userDao = userDao;
        this.mapperService = mapperService;
    }


    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        User user = userDao.findByNick(nick);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь не найден");
        }
        return user;
    }
    @Transactional
    public void create(UserReg dto) {

        User userFromDB = userDao.findByMail(dto.getMail());

        if (userFromDB != null) {
            throw new IllegalArgumentException("пользователь уже существует");
        }

        this.userDao.save(this.mapperService.mapUserCreate(dto));
    }

    public String login(UserLogin dto) {

        UserDetails details = userDao.findByMail(dto.getMail());

        if(!encoder.matches(dto.getPassword(), details.getPassword())){
            throw new IllegalArgumentException("пароль неверный");
        }
        return JwtTokenUtil.generateAccessTokenWithRole(details);
    }
}
