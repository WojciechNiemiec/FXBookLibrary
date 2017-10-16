package com.wniemiec.booklibrary.business.release;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "release")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Release extends AbstractEntity {
    @Column(name = "book_condition")
    private String bookCondition;

    @Column(name = "release_date")
    private ZonedDateTime releaseDate;

    @Column(name = "book_id")
    @ManyToOne
    private Book book;

    @OneToMany
    private List<BookCopy> bookCopies;
}
