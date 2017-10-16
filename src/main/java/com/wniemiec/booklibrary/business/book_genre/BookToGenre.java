package com.wniemiec.booklibrary.business.book_genre;

import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.genre.Genre;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "book_to_genre")
@Data
public class BookToGenre {
    @Column(name = "book_id")
    private Book book;

    @Column(name = "genre_id")
    private Genre genre;
}
