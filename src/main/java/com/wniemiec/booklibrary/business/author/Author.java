package com.wniemiec.booklibrary.business.author;

import com.wniemiec.booklibrary.business.abstracts.AbstractPerson;
import com.wniemiec.booklibrary.business.author_book.AuthorToBook;
import com.wniemiec.booklibrary.business.country.Country;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "author")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractPerson {
    @ManyToOne
    private Country country;

    @OneToMany
    private Set<AuthorToBook> books;
}
