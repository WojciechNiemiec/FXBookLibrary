package com.wniemiec.booklibrary.abstracts;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
