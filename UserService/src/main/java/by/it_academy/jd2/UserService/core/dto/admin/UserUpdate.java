package by.it_academy.jd2.UserService.core.dto.admin;

import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import by.it_academy.jd2.UserService.validation.api.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserUpdate {

    @NotNull
    @Email
    private String mail;
    @NotBlank
    private String nick;
    @NotNull
    private String role;
    @Password
    private String password;
    @NotNull
    private UserStatus status;

    public UserUpdate() {
    }

    public UserUpdate(String mail, String nick, String role, String password, UserStatus status) {
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.password = password;
        this.status = status;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

