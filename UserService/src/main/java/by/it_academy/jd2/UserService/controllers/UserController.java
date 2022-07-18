package by.it_academy.jd2.UserService.controllers;


import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.service.UserHolder;
import by.it_academy.jd2.UserService.service.UserService;

import by.it_academy.jd2.UserService.service.api.IMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserHolder holder;
    private final IMapperService mapperService;

    public UserController(UserService userService, UserHolder holder, IMapperService mapperService) {
        this.userService = userService;
        this.holder = holder;
        this.mapperService = mapperService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserRead> create(@RequestBody UserReg dto) {

        return new ResponseEntity<>(mapperService.mapRead(userService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserLogin dto) {

        return ResponseEntity.ok(userService.login(dto));
    }

    @RequestMapping(value = "/me")
    public UserRead details(){
        return holder.getUser();
    }
}
