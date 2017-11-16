package com.wniemiec.booklibrary.business.publisher;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.address.Address;
import com.wniemiec.booklibrary.business.book.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "publisher")
@Data
@EqualsAndHashCode(callSuper = true)
public class Publisher extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "regon", precision = 14)
    private BigDecimal REGON;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
