package by.it_academy.jd2.classifier.core.dao.api;

import by.it_academy.jd2.classifier.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryDao extends JpaRepository<Category, UUID> {

}
