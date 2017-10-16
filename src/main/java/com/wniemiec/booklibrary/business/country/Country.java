package com.wniemiec.booklibrary.business.country;


import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.address.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "country")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Country extends AbstractEntity {
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "iso_shortcut", length = 2)
    private String ISOShortcut;

    @OneToMany
    private Set<Address> addresses;
}
