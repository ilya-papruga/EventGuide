package by.it_academy.jd2.UserService.core.entity;

import by.it_academy.jd2.UserService.controllers.utils.json.LocalDateTimeSerializer;
import by.it_academy.jd2.UserService.core.entity.enums.UserStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "user_service", name ="users")
public class User implements UserDetails {

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "user_service", name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_mail", referencedColumnName = "mail")},
            inverseJoinColumns = { @JoinColumn(name = "role_name", referencedColumnName = "name")})
    private List<Role> role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    private String password;

    public User() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRole() {
        return role;
    }

    public List<String> getStringRoles(){

        List<String> rolesList = new ArrayList<>();

        for (Role role : getRole()) {
            rolesList.add(role.getName());
        }
        return rolesList;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}

