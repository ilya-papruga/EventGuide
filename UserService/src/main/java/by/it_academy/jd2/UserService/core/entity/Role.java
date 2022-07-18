package by.it_academy.jd2.UserService.core.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "user_service", name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
