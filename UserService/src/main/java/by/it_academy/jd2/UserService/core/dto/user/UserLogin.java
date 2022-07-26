package by.it_academy.jd2.UserService.core.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLogin {

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    private String password;

    public UserLogin() {
    }

    public UserLogin(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
