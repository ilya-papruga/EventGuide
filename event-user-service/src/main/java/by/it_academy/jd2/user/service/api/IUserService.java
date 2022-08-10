package by.it_academy.jd2.user.service.api;

import by.it_academy.jd2.user.core.dto.admin.UserRead;
import by.it_academy.jd2.user.core.dto.user.UserLogin;
import by.it_academy.jd2.user.core.dto.user.UserReg;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IUserService extends UserDetailsService {

    void create(@Valid UserReg dto);

    String login(@Valid UserLogin dto);

    UserRead about();
}
