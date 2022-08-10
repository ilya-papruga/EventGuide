package by.it_academy.jd2.user.controllers;


import by.it_academy.jd2.user.core.dto.admin.UserUpdate;
import by.it_academy.jd2.user.core.dto.page.PageRead;
import by.it_academy.jd2.user.core.dto.admin.UserCreate;
import by.it_academy.jd2.user.core.dto.admin.UserRead;
import by.it_academy.jd2.user.service.api.IAdminService;
import by.it_academy.jd2.user.validation.api.IPathVariableValidator;
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

    private final IAdminService adminService;
    private final IPathVariableValidator validator;
    private final ConversionService conversionService;


    public AdminController(IAdminService adminService, IPathVariableValidator validator, ConversionService conversionService) {
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
