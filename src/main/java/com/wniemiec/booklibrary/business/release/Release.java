package com.wniemiec.booklibrary.business.release;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "book_release")
@Data
@EqualsAndHashCode(callSuper = true)
public class Release extends AbstractEntity {
    @Column(name = "release_date")
    private ZonedDateTime releaseDate;

    @ManyToOne
    private Book book;

    @OneToMany
    @JoinColumn
    private List<BookCopy> bookCopies;
}
