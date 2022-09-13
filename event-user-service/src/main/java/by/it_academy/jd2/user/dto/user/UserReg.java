package by.it_academy.jd2.user.dto.user;

import by.it_academy.jd2.user.validation.api.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserReg {
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String nick;

    @NotBlank
    @Password
    private String password;

    public UserReg() {
    }

    public UserReg(String mail, String nick, String password) {
        this.mail = mail;
        this.nick = nick;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
