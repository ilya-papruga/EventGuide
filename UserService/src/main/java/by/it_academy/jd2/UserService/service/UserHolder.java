package by.it_academy.jd2.UserService.service;

import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.entity.User;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserHolder {

    @Autowired
    IMapperService mapperService;

    public UserRead getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapperService.mapRead(user);

    }

}
