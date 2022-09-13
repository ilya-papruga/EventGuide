package by.it_academy.jd2.user.repository.api;

import by.it_academy.jd2.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

}
