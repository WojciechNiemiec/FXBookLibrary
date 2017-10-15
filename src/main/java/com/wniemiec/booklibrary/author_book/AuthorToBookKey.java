package com.wniemiec.booklibrary.author_book;

import com.wniemiec.booklibrary.author.Author;
import com.wniemiec.booklibrary.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorToBookKey {
    @Column(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "book_id", nullable = false)
    private Book book;
}
