package by.it_academy.jd2.user.service;

import by.it_academy.jd2.user.core.dto.admin.UserRead;
import by.it_academy.jd2.user.core.entity.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserHolder {

    private final ConversionService conversionService;

    public UserHolder(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public UserRead getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return conversionService.convert(user, UserRead.class);
    }
}
