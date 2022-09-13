package by.it_academy.jd2.user.controller;


import by.it_academy.jd2.user.dto.admin.UserRead;
import by.it_academy.jd2.user.dto.user.UserLogin;
import by.it_academy.jd2.user.dto.user.UserReg;

import by.it_academy.jd2.user.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
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
