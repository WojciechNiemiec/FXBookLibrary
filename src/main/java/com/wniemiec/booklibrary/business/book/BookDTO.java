package com.wniemiec.booklibrary.business.book;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private final String ISBN;
    private final String title;
//    private final Set<String> authorsNames;
//    private final Set<String> genresNames;
    private final String publisherName;
//    private final Boolean availability;
}