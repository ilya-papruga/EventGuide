package by.it_academy.jd2.EventConcertService.core.dao.api;

import by.it_academy.jd2.EventConcertService.core.entity.Concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConcertDao extends JpaRepository <Concert, UUID> {

}
