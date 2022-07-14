package by.it_academy.jd2.UserService.controllers;


import by.it_academy.jd2.UserService.core.dto.page.PageRead;
import by.it_academy.jd2.UserService.core.dto.admin.UserCreateUpdate;
import by.it_academy.jd2.UserService.core.dto.admin.UserRead;
import by.it_academy.jd2.UserService.service.api.IMapperService;
import by.it_academy.jd2.UserService.service.api.IAdminService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    private final IAdminService adminService;
    private final IMapperService mapperService;

    public AdminController(IAdminService adminService, IMapperService mapperService) {
        this.adminService = adminService;
        this.mapperService = mapperService;
    }

    @PostMapping
    public ResponseEntity<UserRead> create(@RequestBody UserCreateUpdate dto) {
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
    public ResponseEntity<UserRead> read(@PathVariable UUID uuid) {

        return ResponseEntity.ok(mapperService.mapRead(adminService.readOne(uuid)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <UserRead> update(@PathVariable UUID uuid, @RequestBody UserCreateUpdate dto, @PathVariable Long dt_update) {
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        adminService.update(uuid, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
