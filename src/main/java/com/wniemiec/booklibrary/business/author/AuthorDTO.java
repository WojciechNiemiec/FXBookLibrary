package com.wniemiec.booklibrary.business.author;

import lombok.Data;

@Data
public class AuthorDTO {
    private final Long id;
    private final String name;
    private final String surname;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
