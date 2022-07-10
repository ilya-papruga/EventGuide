package by.it_academy.jd2.EventFilmService.core.dao.api;

import by.it_academy.jd2.EventFilmService.core.entity.Film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFilmDao extends JpaRepository <Film, UUID> {

}
