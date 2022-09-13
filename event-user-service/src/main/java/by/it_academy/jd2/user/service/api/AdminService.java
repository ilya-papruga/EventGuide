package by.it_academy.jd2.user.service.api;

import by.it_academy.jd2.user.dto.admin.UserCreate;
import by.it_academy.jd2.user.dto.admin.UserUpdate;
import by.it_academy.jd2.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface AdminService {

    User create (@Valid UserCreate dto);
    User readOne (UUID uuid);
    Page<User> readPage(Pageable pageable);
    User update(UUID uuid, @Valid UserUpdate dto, LocalDateTime dtUpdate);

}
