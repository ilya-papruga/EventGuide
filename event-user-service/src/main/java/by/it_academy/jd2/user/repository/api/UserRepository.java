package by.it_academy.jd2.user.repository.api;

import by.it_academy.jd2.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    User findByMail(String mail);
    User findByNick(String nick);
}
