package by.it_academy.jd2.film.dto.flim;

import by.it_academy.jd2.film.controller.utils.json.LocalDateTimeSerializer;
import by.it_academy.jd2.film.entity.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class FilmRead {

    private UUID uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;
    private String title;
    private String description;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEvent;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEndOfSale;
    private String type;
    private EventStatus eventStatus;
    private UUID country;
    private Integer releaseYear;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy", locale = "ru")
    private LocalDate releaseDate;
    private Integer duration;

    public FilmRead() {
    }

    public FilmRead(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description,
                    LocalDateTime dtEvent, LocalDateTime dtEndOfSale, String type, EventStatus eventStatus, UUID country,
                    Integer releaseYear, LocalDate releaseDate, Integer duration) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = type;
        this.eventStatus = eventStatus;
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
