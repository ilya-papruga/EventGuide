package by.it_academy.jd2.user.service;

import by.it_academy.jd2.user.controllers.utils.JwtTokenUtil;
import by.it_academy.jd2.user.core.dao.api.IUserDao;
import by.it_academy.jd2.user.core.dto.admin.UserRead;
import by.it_academy.jd2.user.core.dto.user.UserLogin;
import by.it_academy.jd2.user.core.dto.user.UserReg;
import by.it_academy.jd2.user.core.entity.User;

import by.it_academy.jd2.user.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {


    private final PasswordEncoder encoder;
    private final IUserDao userDao;
    private final UserHolder holder;
    private final ConversionService conversionService;

    public UserService(PasswordEncoder encoder, IUserDao userDao, UserHolder holder, ConversionService conversionService) {
        this.encoder = encoder;
        this.userDao = userDao;
        this.holder = holder;
        this.conversionService = conversionService;
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

        this.userDao.save(Objects.requireNonNull(conversionService.convert(dto, User.class)));
    }

    public String login(UserLogin dto) {

        UserDetails details = userDao.findByMail(dto.getMail());

        if(!encoder.matches(dto.getPassword(), details.getPassword())){
            throw new IllegalArgumentException("пароль неверный");
        }
        return JwtTokenUtil.generateAccessTokenWithRole(details);
    }


    public UserRead about() {
        return holder.getUser();
    }
}
