package by.it_academy.jd2.UserService.service.api;

import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;

public interface IUserService {

    void create (UserReg dto);
    void login (UserLogin dto);
    void read ();

}
