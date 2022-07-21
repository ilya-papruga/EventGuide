package by.it_academy.jd2.UserService.service.api;

import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface IAdminService {

    User create (@Valid UserCreateUpdate dto);
    User readOne (UUID uuid);
    Page<User> readPage(Pageable pageable);
    User update(UUID uuid, @Valid UserCreateUpdate dto, LocalDateTime dtUpdate);

}
