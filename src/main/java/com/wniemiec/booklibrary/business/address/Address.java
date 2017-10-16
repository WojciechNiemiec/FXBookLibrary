package com.wniemiec.booklibrary.business.address;

import com.wniemiec.booklibrary.business.country.Country;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@Data
public class Address {
    @Column(name = "city")
    private String city;

    @Column(name = "postalCode")
    private String postalCode;

    @ManyToOne
    private Country country;
}
