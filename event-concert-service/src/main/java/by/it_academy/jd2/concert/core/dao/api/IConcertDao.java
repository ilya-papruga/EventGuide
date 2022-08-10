package by.it_academy.jd2.concert.core.dao.api;

import by.it_academy.jd2.concert.core.entity.Concert;

import by.it_academy.jd2.concert.core.entity.enums.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConcertDao extends JpaRepository <Concert, UUID> {

    Page<Concert> findByEventStatus(EventStatus status, Pageable pageable);
    Page<Concert> findByEventStatusOrAuthor(EventStatus status, String author, Pageable pageable);

}
