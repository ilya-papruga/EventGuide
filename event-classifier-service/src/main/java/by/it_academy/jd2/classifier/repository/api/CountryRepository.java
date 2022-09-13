package by.it_academy.jd2.classifier.repository.api;

import by.it_academy.jd2.classifier.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

}
