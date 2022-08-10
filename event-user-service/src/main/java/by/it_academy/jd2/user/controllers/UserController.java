package by.it_academy.jd2.user.controllers;


import by.it_academy.jd2.user.core.dto.admin.UserRead;
import by.it_academy.jd2.user.core.dto.user.UserLogin;
import by.it_academy.jd2.user.core.dto.user.UserReg;

import by.it_academy.jd2.user.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> create(@RequestBody UserReg dto) {
        userService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserLogin dto) {

        return ResponseEntity.ok(userService.login(dto));
    }

    @RequestMapping(value = "/me")
    public UserRead details(){
        return userService.about();
    }
}
