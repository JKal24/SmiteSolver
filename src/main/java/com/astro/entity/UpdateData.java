package com.astro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity(name="update_data")
public class UpdateData implements Serializable {

    @Id
    private LocalDate date;

    private Double version;

    public UpdateData() {
        this(LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC")), 0.0);
    }

    public UpdateData(LocalDate date, Double version) {
        this.date = date;
        this.version = version;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
}
