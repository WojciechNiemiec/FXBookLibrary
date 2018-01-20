package com.wniemiec.booklibrary.desktop.book;

import com.wniemiec.booklibrary.business.book.BookDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class BookModel {

    private final StringProperty ISBN = new SimpleStringProperty("");
    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty authors = new SimpleStringProperty("");
    private final StringProperty genres = new SimpleStringProperty("");
    private final StringProperty publisher = new SimpleStringProperty("");

    public BookModel(BookDTO dto) {
        this(   dto.getISBN(),
                dto.getTitle(),
                dto.getAuthorsNames(),
                dto.getGenresNames(),
                dto.getPublisherName()
        );
    }

    private BookModel(String ISBN, String title, Set<String> authors, Set<String> genres, String publisher) {
        setISBN(ISBN);
        setTitle(title);
        setAuthors((authors.isEmpty()) ? StringUtils.EMPTY : authors.toString());
        setPublisher(publisher);
        setGenres((genres.isEmpty()) ? StringUtils.EMPTY : genres.toString());
    }

    public String getISBN() {
        return ISBN.get();
    }

    public StringProperty ISBNProperty() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthors() {
        return authors.get();
    }

    public StringProperty authorsProperty() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors.set(authors);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public String getGenres() {
        return genres.get();
    }

    public StringProperty genresProperty() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres.set(genres);
    }
}
