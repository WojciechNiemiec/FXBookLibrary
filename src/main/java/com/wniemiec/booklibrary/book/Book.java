package com.wniemiec.booklibrary.book;

import com.wniemiec.booklibrary.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.author_book.AuthorToBook;
import com.wniemiec.booklibrary.release.Release;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @OneToMany
    private Set<AuthorToBook> authors;

    @OneToMany
    private Set<Release> releases;
}
