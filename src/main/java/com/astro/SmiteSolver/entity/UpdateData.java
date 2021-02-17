package com.astro.SmiteSolver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="update_data")
public class UpdateData implements Serializable {

    @Id
    private LocalDate date;

    private Double version;

    public LocalDate getUpdatedDate() {
        return date;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.date = updatedDate;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
}
