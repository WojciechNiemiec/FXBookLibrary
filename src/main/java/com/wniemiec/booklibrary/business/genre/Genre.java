package com.wniemiec.booklibrary.business.genre;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.book.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "genre")
@Data
@EqualsAndHashCode(callSuper = true)
public class Genre extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;
}
