package com.wniemiec.booklibrary.business.renting;

import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import com.wniemiec.booklibrary.business.reader.Reader;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity(name = "renting")
@Data
public class Renting implements Serializable {
    @EmbeddedId
    private RentingId primaryKey;

    @ManyToOne
    @MapsId("bookCopyId")
    private BookCopy bookCopy;

    @ManyToOne
    @MapsId("readerId")
    private Reader reader;

    @Column(name = "renting_date", nullable = false)
    private ZonedDateTime rentingDate;

    @Column(name = "expected_give_back_date", nullable = false)
    private ZonedDateTime expectedGiveBackDate;

    @Column(name = "actual_give_back_date")
    private ZonedDateTime actualGiveBackDate;
}
