package com.wniemiec.booklibrary.abstracts;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class AbstractPerson extends AbstractEntity {
    @Column
    private String name;
    @Column
    private String surname;
}
