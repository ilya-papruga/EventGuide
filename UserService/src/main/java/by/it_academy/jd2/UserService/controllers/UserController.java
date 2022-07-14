package by.it_academy.jd2.UserService.controllers;


import by.it_academy.jd2.UserService.core.dto.user.UserLogin;
import by.it_academy.jd2.UserService.core.dto.user.UserReg;
import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.service.api.IMapperService;

import by.it_academy.jd2.UserService.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;
    private final IMapperService mapperService;

    public UserController(IUserService userService, IMapperService mapperService) {
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> create(@RequestBody UserReg dto) {
        userService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserRead> read(@RequestBody UserLogin dto) {

        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<UserRead> read() {

        return null;
    }



}
