package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.service.api.IAdminService;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AdminService implements IAdminService {

    private final IUserDao userDao;
    private final IMapperService mapperService;

    public AdminService(IUserDao userDao, IMapperService mapperService) {
        this.userDao = userDao;
        this.mapperService = mapperService;
    }

    @Override
    @Transactional
    public User create(UserCreateUpdate dto) {

        return this.userDao.save(this.mapperService.mapAdminCreate(dto));
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
    public User update(UUID uuid, UserCreateUpdate dto, LocalDateTime dtUpdate) {

        User userDB = readOne(uuid);

        if (!userDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("информация о пользователе уже была обновлена кем-то ранее");
        }
        User user = mapperService.mapUpdate(dto, userDB);

        return this.userDao.save(user);
    }
}
