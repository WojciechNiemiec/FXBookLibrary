package com.wniemiec.booklibrary.business.genre;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.book_genre.BookToGenre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "genre")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Genre extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @OneToMany
    private Set<BookToGenre> books;
}
