package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dao.api.IUserDao;
import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import by.it_academy.jd2.UserService.service.api.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final IMapperService mapperService;

    public UserService(IUserDao userDao, IMapperService mapperService) {
        this.userDao = userDao;
        this.mapperService = mapperService;
    }

    @Override
    public void create(UserReg dto) {
        this.userDao.save(this.mapperService.mapUserCreate(dto));
    }

    @Override
    public void login(UserLogin dto) {

    }

    @Override
    public void read() {

    }
}
