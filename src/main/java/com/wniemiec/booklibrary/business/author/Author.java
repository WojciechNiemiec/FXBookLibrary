package com.wniemiec.booklibrary.business.author;

import com.wniemiec.booklibrary.business.abstracts.AbstractPerson;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.country.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity(name = "author")
@Data
@EqualsAndHashCode(callSuper = true, exclude = "books")
public class Author extends AbstractPerson {
    @ManyToOne
    private Country country;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
