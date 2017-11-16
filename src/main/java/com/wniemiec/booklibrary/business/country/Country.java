package com.wniemiec.booklibrary.business.country;


import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "country")
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends AbstractEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "iso_shortcut", length = 2)
    private String ISOShortcut;
}
