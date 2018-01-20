package com.wniemiec.booklibrary.business.book;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private final Long id;
    private final String ISBN;
    private final String title;
    private Set<String> authorsNames;
    private Set<String> genresNames;
    private final String publisherName;
}