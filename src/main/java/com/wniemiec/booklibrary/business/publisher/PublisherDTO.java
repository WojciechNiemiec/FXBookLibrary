package com.wniemiec.booklibrary.business.publisher;

import lombok.Data;

@Data
public class PublisherDTO {
    private final Long id;
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
