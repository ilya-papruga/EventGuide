package by.it_academy.jd2.UserService.controllers;


import by.it_academy.jd2.UserService.core.dto.page.PageRead;
import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import by.it_academy.jd2.UserService.service.api.IAdminService;
import by.it_academy.jd2.UserService.validation.PathVariableValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    private final IAdminService adminService;
    private final IMapperService mapperService;
    private final PathVariableValidator validator;


    public AdminController(IAdminService adminService, IMapperService mapperService, PathVariableValidator validator) {
        this.adminService = adminService;
        this.mapperService = mapperService;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<UserRead> create(@RequestBody UserCreateUpdate dto, BindingResult bindingResult) {

        return new ResponseEntity<>(mapperService.mapRead(adminService.create(dto)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead<UserRead>> getFilmPage (@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page,size);

        return ResponseEntity.ok(mapperService.mapPage(adminService.readPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.isValidUUID(uuid);

        return ResponseEntity.ok(mapperService.mapRead(adminService.readOne(validUUID)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <UserRead> update(@PathVariable String uuid, @RequestBody UserCreateUpdate dto, @PathVariable Long dt_update) {
        validator.isValidUnixTime(dt_update);
        UUID validUUID = validator.isValidUUID(uuid);

        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        adminService.update(validUUID, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
