package by.it_academy.jd2.EventConcertService.core.dto.flim;

import by.it_academy.jd2.EventConcertService.controllers.utils.json.LocalDateTimeDeserializer;
import by.it_academy.jd2.EventConcertService.core.entity.enums.EventStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertCreateUpdate {

    private String title;
    private String description;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEvent;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEndOfSale;
    private String type;
    private EventStatus eventStatus;
    private UUID category;


    public ConcertCreateUpdate() {
    }

    public ConcertCreateUpdate(String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale,
                               String type, EventStatus eventStatus, UUID category) {
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.type = type;
        this.eventStatus = eventStatus;
        this.category = category;
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

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
