package com.wniemiec.booklibrary.business.book;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.author_book.AuthorToBook;
import com.wniemiec.booklibrary.business.book_genre.BookToGenre;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.release.Release;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "book")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {
    @Column(name = "isbn", unique = true, length = 13)
    private String ISBN;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Publisher publisher;

    @OneToMany
    private Set<AuthorToBook> authors;

    @OneToMany
    private Set<Release> releases;

    @OneToMany
    private Set<BookToGenre> genres;
}
