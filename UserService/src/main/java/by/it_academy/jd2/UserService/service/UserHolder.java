package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserHolder {

    private final IMapperService mapperService;

    public UserHolder(IMapperService mapperService) {
        this.mapperService = mapperService;
    }

    public UserRead getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapperService.mapRead(user);
    }
}
