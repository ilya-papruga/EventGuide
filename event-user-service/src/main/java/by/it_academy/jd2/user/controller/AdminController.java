package by.it_academy.jd2.user.controller;


import by.it_academy.jd2.user.dto.admin.UserUpdate;
import by.it_academy.jd2.user.dto.page.PageRead;
import by.it_academy.jd2.user.dto.admin.UserCreate;
import by.it_academy.jd2.user.dto.admin.UserRead;
import by.it_academy.jd2.user.service.api.AdminService;
import by.it_academy.jd2.user.validation.api.PathVariableValidator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminController {

    private final AdminService adminService;
    private final PathVariableValidator validator;
    private final ConversionService conversionService;


    public AdminController(AdminService adminService, PathVariableValidator validator, ConversionService conversionService) {
        this.adminService = adminService;
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<UserRead> create(@RequestBody UserCreate dto, BindingResult bindingResult) {

        return new ResponseEntity<>(conversionService.convert((adminService.create(dto)),UserRead.class), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead> getFilmPage (@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page-1,size);

        return ResponseEntity.ok(conversionService.convert((adminService.readPage(pageRequest)), PageRead.class));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(conversionService.convert((adminService.readOne(validUUID)), UserRead.class));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <UserRead> update(@PathVariable String uuid, @RequestBody UserUpdate dto, @PathVariable Long dt_update) {
        validator.validUnixTime(dt_update);
        UUID validUUID = validator.validUUID(uuid);

        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        adminService.update(validUUID, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
