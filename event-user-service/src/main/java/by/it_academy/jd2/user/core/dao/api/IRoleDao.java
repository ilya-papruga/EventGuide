package by.it_academy.jd2.user.core.dao.api;

import by.it_academy.jd2.user.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends JpaRepository<Role, String> {
    Role findByName(String name);

}
