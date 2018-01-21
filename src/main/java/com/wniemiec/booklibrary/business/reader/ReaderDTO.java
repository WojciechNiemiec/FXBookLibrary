package com.wniemiec.booklibrary.business.reader;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReaderDTO {
    private final Long id;
    private final BigDecimal pesel;
    private final String name;
    private final String surname;
    private final String city;
    private final String postalCode;
}
