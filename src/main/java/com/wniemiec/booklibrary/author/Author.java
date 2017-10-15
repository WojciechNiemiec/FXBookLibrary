package com.wniemiec.booklibrary.author;

import com.wniemiec.booklibrary.abstracts.AbstractPerson;
import com.wniemiec.booklibrary.author_book.AuthorToBook;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "author")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractPerson {
    @OneToMany
    private Set<AuthorToBook> books;
}
