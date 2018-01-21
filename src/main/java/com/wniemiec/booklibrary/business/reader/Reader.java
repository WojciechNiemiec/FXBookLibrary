package com.wniemiec.booklibrary.business.reader;

import com.wniemiec.booklibrary.business.abstracts.AbstractPerson;
import com.wniemiec.booklibrary.business.address.Address;
import com.wniemiec.booklibrary.business.renting.Renting;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity(name = "reader")
@Data
@EqualsAndHashCode(callSuper = true, exclude = "rentings")
public class Reader extends AbstractPerson {
    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    @Column(name = "pesel", precision = 11)
    private BigDecimal pesel;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "reader")
    private Set<Renting> rentings;
}
