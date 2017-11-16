package com.wniemiec.booklibrary.business.abstracts;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractPerson extends AbstractEntity {
    @Column
    private String name;
    @Column
    private String surname;
}
