package com.wniemiec.booklibrary.business.genre;

import lombok.Data;

@Data
public class GenreDTO {
    private final Long id;
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
