package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.dto.admin.UserCreate;
import by.it_academy.jd2.UserService.core.dto.admin.UserUpdate;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.service.api.IAdminService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AdminService implements IAdminService {

    private final IUserDao userDao;
    private final ConversionService conversionService;

    public AdminService(IUserDao userDao, ConversionService conversionService) {
        this.userDao = userDao;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public User create(UserCreate dto) {

        return this.userDao.save(Objects.requireNonNull(conversionService.convert(dto, User.class)));
    }

    @Override
    public User readOne(UUID uuid) {

        return this.userDao
                .findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("пользователь не найден");
                });
    }

    @Override
    public Page<User> readPage(Pageable pageable) {

        return this.userDao.findAll(pageable);
    }

    @Override
    @Transactional
    public User update(UUID uuid, UserUpdate dto, LocalDateTime dtUpdate) {

        User user = conversionService.convert(dto, User.class);

        User userDB = readOne(uuid);

        user.setUuid(userDB.getUuid());
        user.setDtCreate(userDB.getDtCreate());
        user.setDtUpdate(userDB.getDtUpdate());

        if (!userDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("информация о пользователе уже была обновлена кем-то ранее");
        }

        return this.userDao.save(user);
    }
}
