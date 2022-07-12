package by.it_academy.jd2.EventFilmService.core.dto.flim;

import by.it_academy.jd2.EventFilmService.controllers.utils.json.LocalDateTimeDeserializer;
import by.it_academy.jd2.EventFilmService.core.entity.enums.EventStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class FilmCreateUpdate {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEvent;
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEndOfSale;
    @NotBlank
    private String type;
    @NotNull
    private EventStatus eventStatus;
    @NotNull
    private UUID country;
    @Min(value = 0)
    @NotNull
    private Integer releaseYear;
    @NotBlank
    private String releaseDate;
    @Min(value = 0)
    @Max(value = 300)
    @NotNull
    private Integer duration;

    public FilmCreateUpdate() {
    }

    public FilmCreateUpdate(String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale,
                            String type, EventStatus eventStatus, UUID country, Integer releaseYear,
                            String releaseDate, Integer duration) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
