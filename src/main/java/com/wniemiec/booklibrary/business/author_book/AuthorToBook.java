package com.wniemiec.booklibrary.business.author_book;

import com.wniemiec.booklibrary.business.author.Author;
import com.wniemiec.booklibrary.business.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity(name = "author_to_book")
@Data
public class AuthorToBook {
    @Column(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "book_id", nullable = false)
    private Book book;
}
