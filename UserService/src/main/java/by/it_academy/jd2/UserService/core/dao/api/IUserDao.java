package by.it_academy.jd2.UserService.core.dao.api;

import by.it_academy.jd2.UserService.core.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository <User, UUID> {

}
