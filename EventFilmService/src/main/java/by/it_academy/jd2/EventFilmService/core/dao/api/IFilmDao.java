package by.it_academy.jd2.EventFilmService.core.dao.api;

import by.it_academy.jd2.EventFilmService.core.entity.Film;

import by.it_academy.jd2.EventFilmService.core.entity.enums.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFilmDao extends JpaRepository <Film, UUID> {
    Page<Film> findByEventStatus(EventStatus status, Pageable pageable);
    Page<Film> findByEventStatusOrAuthor(EventStatus status, String author, Pageable pageable);
}
