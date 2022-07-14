package by.it_academy.jd2.UserService.service.api;

import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.dto.page.PageRead;
import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.data.domain.Page;

public interface IMapperService {

    UserRead mapRead(User user);

    User mapAdminCreate(UserCreateUpdate dto);
    User mapUserCreate(UserReg dto);

    User mapUpdate(UserCreateUpdate dto, User userDB);

    PageRead<UserRead> mapPage(Page<User> page);

}
