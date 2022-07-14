package by.it_academy.jd2.UserService.core.dto.admin;

import by.it_academy.jd2.UserService.controllers.utils.json.LocalDateTimeSerializer;
import by.it_academy.jd2.UserService.core.entity.enums.UserRole;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserRead {

    private UUID uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;
    private String mail;
    private String nick;
    private UserRole role;
    private UserStatus status;

    public UserRead() {
    }

    public UserRead(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail, String nick, UserRole role,
                    UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
