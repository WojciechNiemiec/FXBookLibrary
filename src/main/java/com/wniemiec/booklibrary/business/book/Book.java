package com.wniemiec.booklibrary.business.book;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.author.Author;
import com.wniemiec.booklibrary.business.genre.Genre;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.release.Release;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "book")
@Data
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"publisher", "authors", "releases", "genres"})
public class Book extends AbstractEntity {
    @Column(name = "isbn", unique = true, length = 13)
    private String ISBN;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private Set<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Release> releases;

    @ManyToMany
    private Set<Genre> genres;
}
