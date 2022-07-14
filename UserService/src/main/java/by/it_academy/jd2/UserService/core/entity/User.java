package by.it_academy.jd2.UserService.core.entity;

import by.it_academy.jd2.UserService.controllers.utils.json.LocalDateTimeSerializer;
import by.it_academy.jd2.UserService.core.entity.enums.UserRole;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "user_service", name ="users")
public class User {

    @Id
    private UUID uuid;
    @Column(name = "dt_create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    @Version
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;

    private String mail;
    private String nick;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    private String password;

    public User() {
    }

    public User(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail, String nick, UserRole role,
                UserStatus status, String password) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

