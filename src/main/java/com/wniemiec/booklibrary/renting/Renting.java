package com.wniemiec.booklibrary.renting;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity(name = "renting")
@Data
public class Renting implements Serializable {
    @EmbeddedId
    private RentingKey primaryKey;

    @Column(name = "renting_date", nullable = false)
    private ZonedDateTime rentingDate;

    @Column(name = "expected_give_back_date", nullable = false)
    private ZonedDateTime expectedGiveBackDate;

    @Column(name = "actual_give_back_date")
    private ZonedDateTime actualGiveBackDate;
}
