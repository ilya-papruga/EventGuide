package by.it_academy.jd2.UserService.core.dto.admin;

import by.it_academy.jd2.UserService.core.entity.enums.UserRole;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import by.it_academy.jd2.UserService.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserCreateUpdate {

    @NotNull
    @Email
    private String mail;
    @NotBlank
    private String nick;
    @NotNull
    private UserRole role;
    @NotNull
    private UserStatus status;
    @Password
    private String password;

    public UserCreateUpdate() {
    }

    public UserCreateUpdate(String mail, String nick, UserRole role, UserStatus status, String password) {
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
        this.password = password;
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
