package by.it_academy.jd2.UserService.service.api;

import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.UUID;

public interface IAdminService {

    User create (UserCreateUpdate dto);
    User readOne (UUID uuid);
    Page<User> readPage(Pageable pageable);
    User update(UUID uuid, UserCreateUpdate dto, LocalDateTime dtUpdate);

}
