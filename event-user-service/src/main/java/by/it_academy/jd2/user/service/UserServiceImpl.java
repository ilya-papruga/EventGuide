package by.it_academy.jd2.user.service;

import by.it_academy.jd2.user.controller.utils.JwtTokenUtil;
import by.it_academy.jd2.user.repository.api.UserRepository;
import by.it_academy.jd2.user.dto.admin.UserRead;
import by.it_academy.jd2.user.dto.user.UserLogin;
import by.it_academy.jd2.user.dto.user.UserReg;
import by.it_academy.jd2.user.entity.User;

import by.it_academy.jd2.user.service.api.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {


    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserHolder holder;
    private final ConversionService conversionService;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository, UserHolder holder, ConversionService conversionService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.holder = holder;
        this.conversionService = conversionService;
    }


    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        User user = userRepository.findByNick(nick);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь не найден");
        }
        return user;
    }
    @Transactional
    public void create(UserReg dto) {

        User userFromDB = userRepository.findByMail(dto.getMail());

        if (userFromDB != null) {
            throw new IllegalArgumentException("пользователь уже существует");
        }

        this.userRepository.save(Objects.requireNonNull(conversionService.convert(dto, User.class)));
    }

    public String login(UserLogin dto) {

        UserDetails details = userRepository.findByMail(dto.getMail());

        if(!encoder.matches(dto.getPassword(), details.getPassword())){
            throw new IllegalArgumentException("пароль неверный");
        }
        return JwtTokenUtil.generateAccessTokenWithRole(details);
    }


    public UserRead about() {
        return holder.getUser();
    }
}
