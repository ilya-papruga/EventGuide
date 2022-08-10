package by.it_academy.jd2.classifier.core.dao.api;

import by.it_academy.jd2.classifier.core.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ICountryDao extends JpaRepository<Country, UUID> {

}
