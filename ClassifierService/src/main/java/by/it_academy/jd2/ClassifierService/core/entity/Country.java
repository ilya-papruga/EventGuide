package by.it_academy.jd2.ClassifierService.core.entity;

import by.it_academy.jd2.ClassifierService.controllers.utils.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "classifier_service", name ="country")
public class Country {

    @Id
    private UUID uuid;

    @Column(name = "dt_create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @Version
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;
    private String title;
    private String description;


    public Country() {
    }

    public Country(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
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
}

