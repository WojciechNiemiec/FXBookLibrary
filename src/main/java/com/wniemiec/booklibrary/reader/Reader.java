package com.wniemiec.booklibrary.reader;

import com.wniemiec.booklibrary.abstracts.AbstractPerson;
import com.wniemiec.booklibrary.renting.Renting;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity(name = "reader")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Reader extends AbstractPerson {
    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    @Column(name = "pesel")
    private String pesel;

    @OneToMany
    private Set<Renting> rentings;
}
